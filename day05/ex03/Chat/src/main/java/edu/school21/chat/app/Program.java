package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;


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
        User creator = new User(1L, "user1", "password1", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(1L, "chatroom1", creator, new ArrayList());
        Message message = new Message(null, author, room, "New message", LocalDateTime.now());
        try {
            messagesRepository.save(message);
        } catch (SQLException e) {
            System.out.println("Can't save message");
        }
        System.out.println(message.getText());
        message.setText("Update message");
        messagesRepository.update(message);
        System.out.println(message.getText());
        System.out.println(message.getId());
        hikariDataSource.close();
    }

}
