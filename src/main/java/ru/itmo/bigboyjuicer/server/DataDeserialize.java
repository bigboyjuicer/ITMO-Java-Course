package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.command.AbstractCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataDeserialize {

  public static AbstractCommand deserialize(byte[] data) {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new ByteArrayInputStream(data))) {
      return (AbstractCommand) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
