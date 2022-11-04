package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String query = "SELECT * FROM chat.messages WHERE id = " + id;
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                return Optional.empty();
            }
            User user = getUser(rs.getLong(2));
            Chatroom chatroom = getChatroom(rs.getLong(3));
            return Optional.of(new Message(rs.getLong(1), user, chatroom, rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

        private User getUser(Long id) throws SQLException {
        String query = "SELECT * FROM chat.users WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return new User(id, rs.getString(2), rs.getString(3), null, null);
        }
    }
    private Chatroom getChatroom(Long id) throws SQLException {
        String query = "SELECT * FROM chat.chatroom WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return new Chatroom(id, rs.getString(2), null, null);
        }
    }
}
