package io.Ap.StardewValley.Server;

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TcpUdpServer {

    public static void main(String[] args) throws Exception {

    }

    private static final Set<InetSocketAddress> clients = Collections.synchronizedSet(new HashSet<>());

    private DatagramSocket udpSocket;

    public void start() {
        int udpPort = 6000;

        new Thread(() -> {
            try {
                udpSocket = new DatagramSocket(udpPort);
                System.out.println("UDP Server started on port " + udpPort);
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (true) {
                    udpSocket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("UDP Received: " + msg);

                    byte[] reply = ("Echo: " + msg).getBytes();
                    DatagramPacket replyPacket = new DatagramPacket(
                            reply, reply.length, packet.getAddress(), packet.getPort());
                    udpSocket.send(replyPacket);
                    clients.add(new InetSocketAddress(packet.getAddress(), packet.getPort()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleTcpClient(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("TCP Received: " + line);
                out.println("Echo: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendServer (String message) {
        synchronized (clients) {
            for (InetSocketAddress client : clients) {
                try {
                    byte[] data = message.getBytes();
                    DatagramPacket p = new DatagramPacket(data, data.length, client.getAddress(), client.getPort());
                    udpSocket.send(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

