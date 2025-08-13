package io.Ap.StardewValley.Server;

import java.io.*;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Hello TCP Server!");
        System.out.println("Server says: " + in.readLine());

    }
}

