package manager;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.SpaceMarine;
import entity.SpaceMarineSet;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SpaceMarineManager {

    private static final Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();

    public static void save(SpaceMarineSet spaceMarines) {
        try(JsonWriter jsonWriter = new JsonWriter(new BufferedWriter(new FileWriter("data/space_marine.json")))) {
            gson.toJson(spaceMarines, LinkedHashSet.class, jsonWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedHashSet<SpaceMarine> selectAll() {
        try(JsonReader jsonReader = new JsonReader(new StringReader(new Scanner(new FileReader("data/space_marine.json")).tokens().collect(Collectors.joining())))){
            return gson.fromJson(jsonReader, new TypeToken<LinkedHashSet<SpaceMarine>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add(SpaceMarine spaceMarine, SpaceMarineSet spaceMarines){
        spaceMarines.add(spaceMarine);
    }

}
