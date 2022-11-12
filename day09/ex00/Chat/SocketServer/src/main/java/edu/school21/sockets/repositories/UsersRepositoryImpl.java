package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("drop schema if exists server cascade;");
        jdbcTemplate.execute("create schema if not exists server;");
        jdbcTemplate.execute("create table if not exists server.user (\n" +
                "id serial primary key,\n" + "username varchar(40) not null unique,\n" + "password varchar(100) not null);");
    }

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM server.user WHERE id = ?";
        User user = jdbcTemplate.query(query, new Object[]{id}, new int[]{Types.INTEGER}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return user;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM server.user";
        List<User> users = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO server.user (username, password) VALUES(?, ?)";
        if((jdbcTemplate.update(query, entity.getUsername(), entity.getPassword())) == 0)
            System.err.println("User not saved");
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE server.user SET username = ?, password = ? WHERE id = ?";
        if((jdbcTemplate.update(query, entity.getUsername(), entity.getPassword(), entity.getId())) == 0)
            System.err.println("User not update");
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM server.user WHERE id = ?";
        if((jdbcTemplate.update(query, id)) == 0)
            System.err.println("User not found");
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = "SELECT * FROM server.user WHERE username = ?";
        User user = jdbcTemplate.query(query, new Object[]{username}, new int[]{Types.VARCHAR}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
