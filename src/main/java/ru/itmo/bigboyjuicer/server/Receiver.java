package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.manager.FileManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.nio.channels.SelectionKey.OP_READ;

public class Receiver {

  public static DatagramChannel startReceiver() throws IOException {
    DatagramChannel receiver = DatagramChannel.open();
    InetSocketAddress address = new InetSocketAddress("localhost", 2048);
    receiver.bind(address);
    receiver.configureBlocking(false);
    System.out.println("Receiver started at #" + address);
    return receiver;
  }

  public static byte[] receiveMessage(DatagramChannel receiver) throws IOException {
    try {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      SocketAddress socketAddress = receiver.receive(buffer);
      Sender.clientPort = ((InetSocketAddress) socketAddress).getPort();
      System.out.println("Received message from: " + socketAddress);
      buffer.flip();
      byte[] data = new byte[buffer.remaining()];
      buffer.get(data);

      return data;
    } catch (NullPointerException ex) {
      return new byte[]{};
    }
  }
}
