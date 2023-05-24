package ru.itmo.bigboyjuicer.client;

import ru.itmo.bigboyjuicer.command.AbstractCommand;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSerialize {

    public static byte[] serialize(AbstractCommand elem) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(output)) {
            objectOutput.writeObject(elem);
            return output.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
