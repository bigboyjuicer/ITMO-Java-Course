package ru.itmo.bigboyjuicer.server;

import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.manager.FileManager;

import java.io.*;
import java.nio.channels.DatagramChannel;
import java.util.*;

public class Server {
    public static SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        try (DatagramChannel receiver = Receiver.startReceiver()) {
            while (true) {
                byte[] receivedMessage = Receiver.receiveMessage(receiver);
                if(receivedMessage.length > 0) {
                    AbstractCommand command = DataDeserialize.deserialize(receivedMessage);
                    List<String> response = command.execute(spaceMarines);
                    Sender.send(DataSerialize.serialize(response));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
