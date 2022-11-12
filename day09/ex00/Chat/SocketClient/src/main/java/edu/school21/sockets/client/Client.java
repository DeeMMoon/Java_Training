package edu.school21.sockets.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner scanner;
    private PrintWriter writer;
    private Socket socket;
    private Scanner scannerIn = new Scanner(System.in);

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start()
    {
        while (true){
            if (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                System.out.println(message);

                if (message.equalsIgnoreCase("Successful!")) {
                    stop();
                }
            }
            if (scannerIn.hasNextLine()) {
                String message = scannerIn.nextLine();
                writer.println(message);
            }
        }
    }

    private void stop()
    {
        try {
            scanner.close();
            writer.close();
            scannerIn.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }
}
