package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;

public class Main {
    private static int port;
    private final static String ip = "127.0.0.1";
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--server-port=")) {
            System.err.println("Wrong start parameter");
            System.exit(-1);
        }
        port = Integer.parseInt(args[0].substring(14));
        Client client = null;
        try {
            client = new Client(ip, port);
            client.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}
