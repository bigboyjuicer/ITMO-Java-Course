package manager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.SpaceMarine;
import entity.SpaceMarineSet;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
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
  private static Path path = getEnvVar();

  public static void save(SpaceMarineSet spaceMarines) {
    if (isExist()) {
      if (Files.isWritable(path)) {
        try (JsonWriter jsonWriter =
            new JsonWriter(new BufferedWriter(new FileWriter(path.toString()))); ) {
          GSON.toJson(spaceMarines.getSet(), LinkedHashSet.class, jsonWriter);
        } catch (IOException e) {
          System.out.println("Не удалось сохранить данные");
        }
      } else {
        System.out.println("Отсутствуют права на запись");
      }
    }
  }

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
          System.out.println("Файл поврежден");
          createFile();
          selectAll();
        }
      } else {
        System.out.println(
            """
                        Отсутствуют права на чтение файла
                        Дальнейшая работа с коллекцией не доступна
                        """);
        CommandManager.menu("exit", null);
      }
    }
    return null;
  }

  private static void createFile() {
    while (true) {
      try {
        System.out.print("Введите путь, где хотите создать новый файл: ");
        Path newPath = Paths.get(new Scanner(System.in).next());
        Files.createFile(newPath);
        System.out.println("Файл успешно создан");
        path = newPath;
        break;
      } catch (FileAlreadyExistsException ex) {
        System.out.println("Файл с таким названием уже существует");
      } catch (IOException e) {
        System.out.println("Директории, в которую вы хотите сохранить файл, не существует");
      }
    }
  }

  private static boolean isExist() {
    if (!Files.exists(path)) {
      System.out.println("Файла с данными не существует");
      createFile();
    }
    return true;
  }

  private static Path getEnvVar() {
    try {
      return Paths.get(System.getenv("file"));
    } catch (NullPointerException | SecurityException ex) {
      System.out.println("Ошибка считывания переменной окружения");
    }
    return null;
  }
}
