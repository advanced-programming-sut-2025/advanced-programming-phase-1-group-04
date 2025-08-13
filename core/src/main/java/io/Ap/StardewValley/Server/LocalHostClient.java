package io.Ap.StardewValley.Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class LocalHostClient {
    private TcpClient tcpClient;
    private UdpClient udpClient;

    public LocalHostClient() {
        tcpClient = new TcpClient();
        udpClient = new UdpClient();
    }

    public void receiveLobbies() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            assert socket != null;
            socket.receive(receivePacket);
            String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());

        }
        catch (Exception e) {

        }
    }
}
