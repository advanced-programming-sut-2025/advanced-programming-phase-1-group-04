package io.Ap.StardewValley.Server;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class LanClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
        socket.setBroadcast(true);

        byte[] buffer = new byte[256];
        System.out.println("Listening for servers...");
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
            if (message.startsWith("SERVER:")) {
                System.out.println("Found server: " + message);
            }
        }
    }
}
