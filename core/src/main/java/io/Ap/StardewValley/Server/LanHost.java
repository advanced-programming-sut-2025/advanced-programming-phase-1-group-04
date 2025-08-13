package io.Ap.StardewValley.Server;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class LanHost {
    public static void main(String[] args) throws Exception {
        InetAddress broadcast = getBroadcastAddress();
        String myIp = StardewValleyServers.getIPv4();

        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);

        String message = "SERVER:" + myIp + ":54555";
        byte[] buffer = message.getBytes(StandardCharsets.UTF_8);

        System.out.println("Broadcasting on: " + broadcast.getHostAddress());
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcast, 8888);
            socket.send(packet);
            Thread.sleep(2000);
        }
    }

    private static InetAddress getBroadcastAddress() throws Exception {
        for (NetworkInterface ni : java.util.Collections.list(NetworkInterface.getNetworkInterfaces())) {
            for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                if (ia.getBroadcast() != null) {
                    return ia.getBroadcast();
                }
            }
        }
        return InetAddress.getByName("255.255.255.255");
    }
}

