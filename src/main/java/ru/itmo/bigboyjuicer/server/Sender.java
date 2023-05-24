package ru.itmo.bigboyjuicer.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

  public static int clientPort;

  public static void send(byte[] data) {
    try (DatagramSocket socket = new DatagramSocket();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)) {
      byte[] sendData = new byte[1024];
      while (byteArrayInputStream.read(sendData) != -1) {
        DatagramPacket packet =
            new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), clientPort);
        socket.send(packet);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
