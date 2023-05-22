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
          System.out.println("Коллекция успешно было сохранена");
        } catch (IOException e) {
          System.out.println("Не удалось сохранить данные");
        }
      } else {
        System.out.println("Отсутствуют права на запись");
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
          System.out.println("Файл поврежден или в нем находятся не корректные данные");
          suggestCreationOrGettingFile();
          selectAll();
        }
      } else {
        System.out.println(
            """
                        Отсутствуют права на чтение файла
                        Дальнейшая работа с коллекцией не доступна
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
    System.out.println("Введите путь, где хотите создать новый файл");
    while (true) {
      try {
        Path newPath = Paths.get(new Scanner(System.in).next());
        Files.createFile(newPath);
        path = newPath;
        System.out.println("Файл успешно создан");
        break;
      } catch (FileAlreadyExistsException ex) {
        System.out.println("Файл с таким названием уже существует");
      } catch (IOException e) {
        System.out.println("Директории, в которой вы хотите создать файл, не существует");
      } catch (NoSuchElementException ex) {
        System.out.println("Некорректный ввод");
      }
    }
  }

  /**
   * Method that gets file if it's existing or user have enough permissions.
   */
  private static void getExistingFile() {
    System.out.println("Введите путь до существующего файла");
    while (true) {
      Path file = Paths.get(new Scanner(System.in).nextLine().trim());
      try {
        if (Files.exists(file)) {
          path = file;
          System.out.println("Файл успешно найден");
          break;
        }
        System.out.println("Файла не существует");
      } catch (SecurityException ex) {
        System.out.println("Отсутствует доступ к файлу");
      }
    }
  }

  /**
   * Suggest user to choose between creating file, getting existing file or exit from program.
   */
  private static void suggestCreationOrGettingFile() {
    System.out.println(
        "Вы хотите создать файл (1), указать существующий (2) или выйти из программы (3) ?");
    while (true) {
      try {
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
          case (1) -> createFile();
          case (2) -> getExistingFile();
          case (3) -> System.exit(0);
          default -> System.out.println("Неверное значение");
        }
        break;
      } catch (InputMismatchException ex) {
        System.out.println("Поле должно быть числом");
      } catch (NoSuchElementException ex) {
        System.out.println("Некорректный ввод");
      }
    }
  }

  /**
   * Checks if file is existing
   * @return
   */
  private static boolean isExist() {
    if (!Files.exists(path)) {
      System.out.println("Файла с данными не существует");
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
      System.out.println("Ошибка считывания переменной окружения");
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
              Неверная команда.
              help : вывести справку по доступным командам.
              """);
    }
    return null;
  }
}
