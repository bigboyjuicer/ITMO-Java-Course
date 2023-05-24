package ru.itmo.bigboyjuicer.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Receiver {

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
    Sender.clientPort = ((InetSocketAddress) socketAddress).getPort();
    System.out.println(socketAddress);
    System.out.println("Received message from: " + socketAddress);
    buffer.flip();
    byte[] data = new byte[buffer.remaining()];
    buffer.get(data);
    return data;
  }
}
