package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplateRepository")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return namedParameterJdbcTemplate.query("SELECT * FROM models.user WHERE id = :id",
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = namedParameterJdbcTemplate.query("SELECT * FROM models.user", new BeanPropertyRowMapper<>(User.class));
        if(userList.isEmpty())
            return null;
        else
            return userList;
    }

    @Override
    public void save(User entity) {
        if(namedParameterJdbcTemplate.update("INSERT INTO models.user (id, email) VALUES (:id, :email)", new MapSqlParameterSource().addValue("id", entity.getId()).addValue("email", entity.getEmail())) == 0)
            System.err.println("Can't save user with id = " + entity.getId());
    }

    @Override
    public void update(User entity) {
        if(namedParameterJdbcTemplate.update("UPDATE models.user SET email = :email WHERE id = :id", new MapSqlParameterSource().addValue("id", entity.getId()).addValue("email", entity.getEmail())) == 0)
        System.err.println("Can't update user with id = " + entity.getId());
    }

    @Override
    public void delete(Long id) {
        if(namedParameterJdbcTemplate.update("DELETE FROM models.user WHERE id = :id", new MapSqlParameterSource().addValue("id", id)) == 0)
            System.err.println("User with id = " + id + " not found");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = namedParameterJdbcTemplate.query("SELECT * FROM models.user WHERE email = :email",
                new MapSqlParameterSource().addValue("email", email),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        if (user == null)
            return null;
        else return Optional.of(user);
    }
}
