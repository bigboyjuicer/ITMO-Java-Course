package ru.itmo.bigboyjuicer.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DataDeserialize {

    public static List<String> deserialize(byte[] data) {
        try (ObjectInputStream objectInputStream =
                     new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (List<String>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return List.of("Неудалось получить данные");
        }
    }

}
