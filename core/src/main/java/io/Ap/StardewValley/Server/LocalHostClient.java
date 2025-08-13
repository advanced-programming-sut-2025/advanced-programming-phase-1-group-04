package io.Ap.StardewValley.Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LocalHostClient {
    private TcpClient tcpClient;
    private UdpClient udpClient;

    public LocalHostClient() {
        tcpClient = new TcpClient();
        udpClient = new UdpClient();
    }

    public String receiveLobbies() {
        return udpClient.receiveMessage();
    }
}
