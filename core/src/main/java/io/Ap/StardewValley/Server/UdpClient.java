package io.Ap.StardewValley.Server;

import java.net.*;

public class UdpClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("127.0.0.1");

        String msg = "Hello UDP Server!";
        byte[] buffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 6000);
        socket.send(packet);

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);

        String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Server says: " + reply);
    }
}

