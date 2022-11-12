package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Component
public class Server {

    private Scanner scanner;
    private UsersService usersService;
    private PrintWriter writer;
    private Socket socket;
    private ServerSocket serverSocket;

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }
    public void start(int port)
    {
        String username = null;
        String password = null;
        String message;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello from server!");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            stop();
        }
        while (true)
        {
            try {
                if (scanner.hasNextLine()) {
                    message = scanner.nextLine().trim();
                    if (!message.equalsIgnoreCase("signUp"))
                        throw new RuntimeException("Wrong input");
                }
                writer.println("Enter username: ");
                if (scanner.hasNextLine())
                    username = scanner.nextLine().trim();
                writer.println("Enter password: ");
                if (scanner.hasNextLine())
                    password = scanner.nextLine().trim();
                usersService.signUp(new User(username, password));
                writer.println("Successful!");
               stop();
            } catch (RuntimeException e)
            {
                System.out.println(e.getMessage());
                writer.println(e.getMessage());
            }
        }
    }

    public void stop()
    {
        try {
            if(writer != null)
                writer.close();
            if (scanner != null)
                scanner.close();
            if (socket != null)
                socket.close();
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }
}
