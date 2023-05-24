package ru.itmo.bigboyjuicer.manager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/***
 * Class that manages the operation of the file
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class FileManager {

  private static final Gson GSON = Converters.registerLocalDateTime(new GsonBuilder()).create();
  public static Path path = getEnvVar();


  /**
   * Method provides file saving.
   * It takes a file and output all data from collection,
   * before that parsing all data to json format.
   * @param spaceMarines
   */
  public static void save(SpaceMarineSet spaceMarines) {
    if (isExist()) {
      if (Files.isWritable(path)) {
        try (JsonWriter jsonWriter =
            new JsonWriter(new BufferedWriter(new FileWriter(path.toString()))); ) {
          GSON.toJson(spaceMarines.getSet(), LinkedHashSet.class, jsonWriter);
          System.out.println("The collection has been successfully saved");
        } catch (IOException e) {
          System.out.println("Unable to save data");
        }
      } else {
        System.out.println("No write permissions");
      }
    }
  }

  /**
   * Method provides getting data from file.
   * It takes a file and return all data,
   * before that parsing them from json.
   * @return
   */
  public static LinkedHashSet<SpaceMarine> selectAll() {
    if (isExist()) {
      if (Files.isReadable(path)) {
        try (JsonReader jsonReader =
            new JsonReader(
                new StringReader(
                    new Scanner(new FileReader(path.toString()))
                        .tokens()
                        .collect(Collectors.joining())))) {
          return GSON.fromJson(
              jsonReader, new TypeToken<LinkedHashSet<SpaceMarine>>() {}.getType());
        } catch (IOException | JsonSyntaxException e) {
          System.out.println("The file is damaged or contains incorrect data");
          suggestCreationOrGettingFile();
          selectAll();
        }
      } else {
        System.out.println(
                        """
                        No read permissions
                        No further work with the collection is available
                        """);
        System.exit(0);
      }
    }
    return null;
  }

  /**
   * Method that creates file if it's not existing or
   * if the directory, that listed in Path, is existing.
   */
  private static void createFile() {
    System.out.println("Write the path where you want to create the new file");
    while (true) {
      try {
        Path newPath = Paths.get(new Scanner(System.in).next());
        Files.createFile(newPath);
        path = newPath;
        System.out.println("File successfully created");
        break;
      } catch (FileAlreadyExistsException ex) {
        System.out.println("A file with this name already exists");
      } catch (IOException e) {
        System.out.println("The directory in which you want to create the file does not exist");
      } catch (NoSuchElementException ex) {
        System.out.println("Incorrect input");
      }
    }
  }

  /**
   * Method that gets file if it's existing or user have enough permissions.
   */
  private static void getExistingFile() {
    System.out.println("Write the path to an existing file");
    while (true) {
      Path file = Paths.get(new Scanner(System.in).nextLine().trim());
      try {
        if (Files.exists(file)) {
          path = file;
          System.out.println("File successfully found");
          break;
        }
        System.out.println("The file does not exist");
      } catch (SecurityException ex) {
        System.out.println("No file access");
      }
    }
  }

  /**
   * Suggest user to choose between creating file, getting existing file or exit from program.
   */
  private static void suggestCreationOrGettingFile() {
    System.out.println(
        "Do you want to create a file (1), specify an existing file (2) or exit the program (3) ?");
    while (true) {
      try {
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
          case (1) -> createFile();
          case (2) -> getExistingFile();
          case (3) -> System.exit(0);
          default -> System.out.println("Incorrect value");
        }
        break;
      } catch (InputMismatchException ex) {
        System.out.println("The field must be a number");
      } catch (NoSuchElementException ex) {
        System.out.println("Incorrect input");
      }
    }
  }

  /**
   * Checks if file is existing
   * @return
   */
  private static boolean isExist() {
    if (!Files.exists(path)) {
      System.out.println("The file does not exist");
      suggestCreationOrGettingFile();
    }
    return true;
  }

  /**
   * Method that gets environment variable. If variable
   * doesn't exist, it calls suggestCreationGettingFile() method.
   * @return Path
   */
  private static Path getEnvVar() {
    try {
      return Paths.get(System.getenv("FILE"));
    } catch (NullPointerException | SecurityException ex) {
      System.out.println("Environment variable read error");
      suggestCreationOrGettingFile();
    }
    return path;
  }

  public static SpaceMarine parseJson(String json) {
    try {
      return GSON.fromJson(json, SpaceMarine.class);
    } catch(JsonSyntaxException ex) {
      System.out.println(
              """
              Wrong command.
              help : display available commands.
              """);
    }
    return null;
  }
}
