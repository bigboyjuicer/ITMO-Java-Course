package ru.itmo.bigboyjuicer.client;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {

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
        Receiver.clientPort = sender.socket().getLocalPort();
    }

}
