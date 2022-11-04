package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public boolean save(Message message) {

        if (message.getAuthor() == null || message.getAuthor().getId() == null || message.getChatroom() == null
        || message.getChatroom().getId() == null || message.getChatroom().getOwner() == null || message.getChatroom().getOwner().getId() == null
        || message.getText() == null || message.getText().length() == 0)
            throw new NotSavedSubEntityException("Parameter with null");
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()){
            String query = "SELECT * FROM chat.users WHERE id = " + message.getAuthor().getId();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next())
                throw new NotSavedSubEntityException("User with id = " + message.getAuthor().getId() + " doesn't exist");
            query = "SELECT * FROM chat.chatroom WHERE id = " + message.getChatroom().getId();
            resultSet = statement.executeQuery(query);
            if (!resultSet.next())
                throw new NotSavedSubEntityException("Chatroom with id = " + message.getChatroom().getId() + " doesn't exist");
            String time = "'null'";
            if (message.getLocalDateTime() != null)
                time = "'" + Timestamp.valueOf(message.getLocalDateTime()) + "'";
            resultSet = statement.executeQuery("INSERT INTO chat.messages (author, room, text, localdatetime)" +
                    "VALUES (" + message.getAuthor().getId() + ", " + message.getChatroom().getId() + ", '" + message.getText() + "', " + time + ") RETURNING id");
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("End of table");
            }
            message.setId(resultSet.getLong(1));
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Message message) {
        if (message.getAuthor() == null || message.getAuthor().getId() == null || message.getChatroom() == null
                || message.getChatroom().getId() == null || message.getChatroom().getOwner() == null || message.getChatroom().getOwner().getId() == null
                || message.getText() == null || message.getText().length() == 0)
            throw new NotSavedSubEntityException("Parameter with null");
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()){
            String queryMessage = "SELECT * FROM chat.messages WHERE id = " + message.getId();
            String queryUser = "SELECT * FROM chat.users WHERE id = " + message.getAuthor().getId();
            String query = "UPDATE chat.messages SET author = ?,  room = ?," + " text = ?,  localdatetime= ? WHERE id = ?";
            ResultSet resultSet = statement.executeQuery(queryUser);
            statement.close();
            PreparedStatement preparedStatement = connection.prepareStatement(queryMessage);
            resultSet = preparedStatement.executeQuery();
            if (message.getText() == null) {
                message.setText("");
            }
            if (!resultSet.next()) {
                save(message);
                return true;
            }
            Timestamp time = null;
            if (message.getLocalDateTime() != null) {
                time = Timestamp.valueOf(message.getLocalDateTime());
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, message.getAuthor().getId());
            preparedStatement.setLong(2, message.getChatroom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, time);
            preparedStatement.setLong(5, message.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
