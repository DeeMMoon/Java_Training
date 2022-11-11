package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM models.user WHERE id = " + id;
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next())
                return null;
            return new User(resultSet.getLong(1), resultSet.getString(2));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM models.user";
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                userList.add(new User(resultSet.getLong(1), resultSet.getString(2)));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        if(userList.isEmpty())
            return null;
        else
            return userList;
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO models.user (id, email) VALUES (" + entity.getId() + ", '" + entity.getEmail() + "');";
        try(Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(query);
            if (result == 0)
                System.err.println("Can't save user with id = " + entity.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE models.user SET email = ? WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setLong(2, entity.getId());
            int result = preparedStatement.executeUpdate();
            if(result == 0)
                System.err.println("Can't update user with id = " + entity.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM models.user WHERE id = " + id;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int result = preparedStatement.executeUpdate();
            if(result == 0)
                System.err.println("User with id = " + id + " not found");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM models.user WHERE email = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next())
                return Optional.empty();
            return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
