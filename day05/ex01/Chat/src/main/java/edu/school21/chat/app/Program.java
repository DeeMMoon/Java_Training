package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;


public class Program {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
    private static HikariDataSource hikariDataSource;
    private static MessagesRepository messagesRepository;

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_CONNECTION);
        config.setPassword(PASSWORD);
        config.setUsername(USERNAME);
        hikariDataSource = new HikariDataSource(config);
        messagesRepository = new MessagesRepositoryJdbcImpl(hikariDataSource);
        System.out.println("Enter a message ID");
        Scanner scanner = new Scanner(System.in);
        Optional<Message> messageOptional = messagesRepository.findById(scanner.nextLong());
        Message message = null;
        if (messageOptional.isPresent())
            message = messageOptional.get();
        if(message == null)
            System.out.println("Message doesn't exist");
        System.out.println(message);
        hikariDataSource.close();
    }

}
