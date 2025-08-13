package io.Ap.StardewValley.Server;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LanHost {
    public static void main(String[] args) throws Exception {
        String myIp = StardewValleyServers.getIPv4(); // این رو بذار متد خودت که IP رو می‌ده
        DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);

        String message = "SERVER:" + myIp + ":54555";
        byte[] buffer = message.getBytes(StandardCharsets.UTF_8);

        System.out.println("LAN Host started. Broadcasting every 2 seconds...");

        while (true) {
            for (InetAddress broadcast : getAllBroadcastAddresses()) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcast, 54555);
                socket.send(packet);
                System.out.println("Broadcast sent to: " + broadcast.getHostAddress());
            }
            Thread.sleep(2000);
        }
    }

    private static List<InetAddress> getAllBroadcastAddresses() throws Exception {
        List<InetAddress> list = new ArrayList<>();
        for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
            for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                InetAddress broadcast = ia.getBroadcast();
                if (broadcast != null) {
                    list.add(broadcast);
                }
            }
        }
        if (list.isEmpty()) {
            list.add(InetAddress.getByName("255.255.255.255"));
        }
        return list;
    }
}


