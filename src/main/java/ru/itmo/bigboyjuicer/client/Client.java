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
  public static int clientPort;

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      try (DatagramChannel sender = startSender()) {
        AbstractCommand command = validate(scanner.nextLine().trim());
        if (command != null) {
          sendMessage(sender, serialize(command), SERVER);
        } else {
            continue;
        }
      } catch (NoSuchElementException ex) {
        System.exit(0);
      }
      catch (IOException e) {
        throw new RuntimeException(e);
      }
      List<String> response = deserialize(receive());
      response.forEach(System.out::println);
    }
  }

  public static byte[] receive() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try (DatagramSocket socket = new DatagramSocket(clientPort)) {
      //System.out.println(socket.getLocalPort());
      socket.setSoTimeout(500);
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      while (true) {
        socket.receive(packet);
        byteArrayOutputStream.write(data);
        byteArrayOutputStream.flush();
      }
    } catch (SocketTimeoutException e) {

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    //System.out.println(byteArrayOutputStream.toByteArray().length);
    return byteArrayOutputStream.toByteArray();
  }

  public static List<String> deserialize(byte[] data) {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new ByteArrayInputStream(data))) {
      return (List<String>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      return List.of("Неудалось получить данные");
    }
  }

  public static <T> byte[] serialize(T elem) {
    try (ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(output)) {
      objectOutput.writeObject(elem);
      return output.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static DatagramChannel startSender() throws IOException {
    DatagramChannel sender = DatagramChannel.open();
    sender.bind(null);
    sender.configureBlocking(false);
    return sender;
  }

  public static void sendMessage(DatagramChannel sender, byte[] data, SocketAddress receiverAddress)
      throws IOException {
    ByteBuffer buffer = ByteBuffer.wrap(data);
    sender.send(buffer, receiverAddress);
    clientPort = sender.socket().getLocalPort();
  }

  public static AbstractCommand validate(String msg) {
    Scanner scanner = new Scanner(msg);

    try {
      Command commandType = Command.valueOf(scanner.next().trim().toUpperCase());
      HISTORY.add(commandType.getCommand().getName());

      if(commandType.name().equals("EXIT")) System.exit(0);
      if (commandType.name().equals("HISTORY")){
        HISTORY.forEach(System.out::println);
        return null;
      }

      if (commandType.isContainsArg() == scanner.hasNextLine()) {
        AbstractCommand command = commandType.getCommand();
        if(commandType.name().equals("ADD") || commandType.name().equals("ADD_IF_MIN") || commandType.name().equals("REMOVE_GREATER")) command.setElement(EntityBuilder.spaceMarineBuilder());
        if (commandType.isContainsArg()) {
          command.setArg(scanner.nextLine().trim());
        }
        return command;
      }
      System.out.println("Неверный формат команды");
    } catch (NoSuchElementException | IllegalArgumentException ex) {
      System.out.println("Неверная команда");
    }
    return null;
  }
}
