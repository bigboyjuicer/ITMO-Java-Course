package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.manager.FileManager;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.*;

public class Server {

  public static void main(String[] args) {
    /*try {
      DatagramChannel sender = startSender();
      sendMessage(sender, data, new InetSocketAddress("localhost", 2048));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/

    SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());

    try (DatagramChannel receiver = Receiver.startReceiver()) {
      while (true) {
        byte[] receivedMessage = Receiver.receiveMessage(receiver);
        AbstractCommand command = DataDeserialize.deserialize(receivedMessage);
        List<String> response = command.execute(spaceMarines);
        Sender.send(DataSerialize.serialize(response));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
