package manager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
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

public class FileManager {

    private static final Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
    private static Path path = Paths.get("data/space_marine");

    public static void save(SpaceMarineSet spaceMarines) {
        if (isExist()) {
            if (Files.isWritable(path)) {
                try {
                    JsonWriter jsonWriter = new JsonWriter(new BufferedWriter(new FileWriter(path.toString())));
                    gson.toJson(spaceMarines.getSet(), LinkedHashSet.class, jsonWriter);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Отсутствуют права на запись");
            }
        }
    }

    public static LinkedHashSet<SpaceMarine> selectAll() {
        if (isExist()) {
            if (Files.isReadable(path)) {
                try (JsonReader jsonReader = new JsonReader(new StringReader(new Scanner(new FileReader(path.toString())).tokens().collect(Collectors.joining())))) {
                    return gson.fromJson(jsonReader, new TypeToken<LinkedHashSet<SpaceMarine>>() {}.getType());
                } catch (FileNotFoundException e) {
                    System.out.println("Файла с данными не существует");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Отсутствуют права на чтение");
            }
        }
        return null;
    }

    private static void createFile() {
        while (!Files.exists(path)) {
            try {
                System.out.print("Введите путь, где хотите создать файл: ");
                Path newPath = Paths.get(new Scanner(System.in).next());
                Files.createFile(newPath);
                path = newPath;
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

}
