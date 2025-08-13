package io.Ap.StardewValley.Server;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    private DatagramSocket socket;

    public UdpClient(){
        try {
            socket = new DatagramSocket(6000);
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

    }

    public String receiveMessage () {
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        try {
            socket.receive(receivePacket);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}

