package ru.itmo.bigboyjuicer.client;

import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.server.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.type.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

public class Client {

  public static final InetSocketAddress SERVER = new InetSocketAddress("localhost", 2048);
  public static SocketAddress myAddress;

  public static final Map<String, Boolean> COMMANDS = new HashMap<>();

  static {
    COMMANDS.put("show", false);
  }

  public static void main(String[] args) {

    /*byte[] data = receive();

    LinkedHashSet<SpaceMarine> set = deserialize(data);

    set.forEach(System.out::println);*/
    Scanner scanner = new Scanner(System.in);

    try(DatagramChannel sender = startSender()) {
      while (true) {
        AbstractCommand command = validate(scanner.nextLine().trim());
        if(command != null) {
          sendMessage(sender, serialize(command), SERVER);
          List<String> response = deserialize(receive());
          response.forEach(System.out::println);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] receive() {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try (DatagramSocket socket = new DatagramSocket(2049)) {
      System.out.println(socket.getLocalPort());
      socket.setSoTimeout(500);
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      while(true) {
        System.out.println(socket.isConnected());
        socket.receive(packet);
        byteArrayOutputStream.write(data);
        byteArrayOutputStream.flush();
      }
    } catch (SocketTimeoutException e) {

    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }

    System.out.println(byteArrayOutputStream.toByteArray().length);
    return byteArrayOutputStream.toByteArray();
  }

  public static List<String> deserialize(byte[] data) {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new ByteArrayInputStream(data))) {
      return (List<String>) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
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
  }

  public static DatagramChannel startReceiver() throws IOException {
    DatagramChannel receiver = DatagramChannel.open();
    InetSocketAddress address = new InetSocketAddress("localhost", 2048);
    receiver.bind(address);

    System.out.println("Receiver started at #" + address);
    return receiver;
  }

  public static byte[] receiveMessage(DatagramChannel receiver) throws IOException {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    SocketAddress senderAddress = receiver.receive(buffer);
    buffer.flip();
    byte[] data = new byte[buffer.remaining()];
    buffer.get(data);
    System.out.println(Arrays.toString(data));
    return data;
  }

  public static AbstractCommand validate(String msg) {
    Scanner scanner = new Scanner(msg);
    try {
      Command commandType = Command.valueOf(scanner.next().trim().toUpperCase());
      if(commandType.isContainsArg() == scanner.hasNextLine()) {
        System.out.println("Все круто");
        AbstractCommand command = commandType.getCommand();
        if(commandType.isContainsArg()) {
          command.setArg(scanner.nextLine());
        }
        return command;
      }
      System.out.println("Неверный формат команды");
    } catch (IllegalArgumentException ex) {
      System.out.println("Неверная команда");
    }
    return null;
  }
}
