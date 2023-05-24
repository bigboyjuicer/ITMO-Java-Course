package ru.itmo.bigboyjuicer.client;

import ru.itmo.bigboyjuicer.builder.EntityBuilder;
import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.type.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

public class Client {
  public static final Queue<String> HISTORY = new ArrayDeque<>(7);
  public static final InetSocketAddress SERVER = new InetSocketAddress("localhost", 2048);

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      try {
        menu(scanner.nextLine());
      } catch (NoSuchElementException ex) {
        System.exit(0);
      }
    }
  }

  public static void menu(String input) {
    try (DatagramChannel sender = Sender.startSender()) {
      AbstractCommand command = CommandInputValidator.validate(input.trim());
      if (command != null) {
        Sender.sendMessage(sender, DataSerialize.serialize(command), SERVER);
      } else {
        return;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    List<String> response = DataDeserialize.deserialize(Receiver.receive());
    response.forEach(System.out::println);
  }
}
