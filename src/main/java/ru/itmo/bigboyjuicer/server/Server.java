package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.server.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.server.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.manager.FileManager;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

public class Server {
  public static SpaceMarineSet spaceMarineSet = new SpaceMarineSet(FileManager.selectAll());

  public static void main(String[] args) {
    /*try {
      DatagramChannel sender = startSender();
      sendMessage(sender, data, new InetSocketAddress("localhost", 2048));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/

    try (DatagramChannel receiver = startReceiver()) {
      while (true) {
        byte[] receivedMessage = receiveMessage(receiver);
        AbstractCommand command = deserialize(receivedMessage);
        List<String> response = command.execute();
        send(serialize(response));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void send(byte[] data) {
    try (DatagramSocket socket = new DatagramSocket();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)) {
      byte[] sendData = new byte[1024];
      while(byteArrayInputStream.read(sendData) != -1){
        DatagramPacket packet = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 2049);
        socket.send(packet);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] serialize(List<String> response) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        )
    {
      objectOutputStream.writeObject(response);
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T deserialize(byte[] data) {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new ByteArrayInputStream(data))) {
      return (T) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
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
    SocketAddress socketAddress = receiver.receive(buffer);
    System.out.println("Received message from: " + socketAddress);
    buffer.flip();
    byte[] data = new byte[buffer.remaining()];
    buffer.get(data);
    return data;
  }

  public static DatagramChannel startSender() throws IOException {
    DatagramChannel sender = DatagramChannel.open();
    sender.bind(null);
    sender.configureBlocking(false);
    return sender;
  }

  public static void sendMessage(DatagramChannel sender, byte[] data, SocketAddress receiverAddress)
      throws IOException {
    System.out.println(Arrays.toString(data));
    ByteBuffer buffer = ByteBuffer.wrap(data);
    while (buffer.hasRemaining()) {
      byte[] newData = new byte[1024];
      buffer.get(newData);
      sender.send(ByteBuffer.wrap(newData), receiverAddress);
    }

    sender.close();
  }
}
