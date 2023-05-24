package ru.itmo.bigboyjuicer.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

public class Receiver {

    public static int clientPort;
    public static byte[] receive() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (DatagramSocket socket = new DatagramSocket(clientPort)) {
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

        return byteArrayOutputStream.toByteArray();
    }

}
