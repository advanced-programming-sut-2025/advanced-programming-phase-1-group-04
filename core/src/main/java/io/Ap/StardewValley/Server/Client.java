package io.Ap.StardewValley.Server;

import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Player.Player;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}


