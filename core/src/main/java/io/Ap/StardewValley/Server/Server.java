package io.Ap.StardewValley.Server;

import io.Ap.StardewValley.Model.Player.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    private final int tcpPort;
    private final int udpPort;
    private final String hostName;
    private final boolean visibility;
    private final String IPv4Address;
    private final ArrayList<Player> players;
    private final ArrayList<Client> clients;

    public Server(String hostName, boolean visibility) {
        this.tcpPort = StardewValleyServers.ports.get(0);
        this.udpPort = StardewValleyServers.ports.get(1);
        StardewValleyServers.ports.remove(0);
        StardewValleyServers.ports.remove(0);
        try {
            Start();
        }
        catch (Exception e) {

        }
        this.hostName = hostName;
        this.visibility = visibility;
        StardewValleyServers.addServer(this);
        this.IPv4Address = StardewValleyServers.getIPv4();
        this.players = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void Start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(tcpPort);
        System.out.println("TCP Server started on port " + tcpPort);

        DatagramSocket udpSocket = new DatagramSocket(udpPort);
        System.out.println("UDP Server started on port " + udpPort);

        Thread udpThread = new Thread(() -> {
            try {
                byte[] buffer = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (true) {
                    udpSocket.receive(packet);

                    byte[] data = new byte[packet.getLength()];
                    System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());

                    ChatMessage pos = KryoUtils.deserialize(data, ChatMessage.class);
                    System.out.println("Received UDP PlayerPosition: " + pos.text);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        udpThread.start();
    }

    public static void main(String[] args) throws Exception {
        /*ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("TCP Server started on port 5555");

        DatagramSocket udpSocket = new DatagramSocket(5556);
        System.out.println("UDP Server started on port 5556");

        Thread udpThread = new Thread(() -> {
            try {
                byte[] buffer = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (true) {
                    udpSocket.receive(packet);

                    byte[] data = new byte[packet.getLength()];
                    System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());

                    ChatMessage pos = KryoUtils.deserialize(data, ChatMessage.class);
                    System.out.println("Received UDP PlayerPosition: " + pos.text);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        udpThread.start();

        //clientSocket.close();
        //udpSocket.close();
        //serverSocket.close();*/
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public String getHostName() {
        return hostName;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getIPv4Address() {
        return IPv4Address;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Client added to list of players");
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}

