package io.Ap.StardewValley.Server;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.IntStream;

public class StardewValleyServers {
    private final static ArrayList<Server> servers = new ArrayList<>();
    public final static ArrayList<Integer> ports = new ArrayList<>(IntStream.rangeClosed(1000, 1500)
            .boxed()
            .toList());
    public static void main(String[] args) throws Exception {
        while (true) {

        }
    }

    public static void addServer(Server server) {
        servers.add(server);
    }

    public static String getIPv4()
    {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isUp() || ni.isLoopback()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static ArrayList<Server> getServers() {
        return servers;
    }

    /*public static Server getServer (String hostName) {
        for (Server server : servers) {
            if (server.getHostName().equals(hostName)) {
                return server;
            }
        }
        return null;
    }

    public static Server getServerByIP (String ipv4Address) {
        for (Server server : servers) {
            if (server.getIPv4Address().equals(ipv4Address)) {
                return server;
            }
        }
        return null;
    }*/
}