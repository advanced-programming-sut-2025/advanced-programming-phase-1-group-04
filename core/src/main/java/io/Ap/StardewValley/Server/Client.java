package io.Ap.StardewValley.Server;

import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Player.Player;

import java.io.*;
import java.net.*;

public class Client {
    private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int serverUdpPort;
    private int playerId;
    private String playerName;

    public Client(String ipAddress , int port, int playerId, String playerName) throws Exception {
        this.udpSocket = new DatagramSocket();
        this.serverAddress = InetAddress.getByName(ipAddress);
        this.serverUdpPort = port;
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public static void main(String[] args) throws Exception {

    }

    public void updatePosition() throws Exception {
        Player player = App.getGame().getCurrentPlayer();
        int playerId = player.getId();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.text = "id = " + playerId + " Coordinate: "
                + player.getCoordinate().getX() + "," + player.getCoordinate().getY();;
        byte[] data = KryoUtils.serialize(chatMessage);
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverUdpPort);
        udpSocket.send(packet);
    }

    public DatagramSocket getUdpSocket() {
        return udpSocket;
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public int getServerUdpPort() {
        return serverUdpPort;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }
}


