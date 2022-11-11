package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        createTable(context);
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findAll());
        Long id = 1L;
        for (int i = 0; i < 5; i++)
            usersRepositoryJdbc.save(new User(id++, "user"));
        for (int i = 0; i < 5; i++)
            usersRepositoryJdbcTemplate.save(new User(id++, "userTemplate"));
        System.out.println("JDBC : ");
        for (User user : usersRepositoryJdbc.findAll())
            System.out.println(user.toString());
        System.out.println("SPRING : ");
        for (User user : usersRepositoryJdbcTemplate.findAll())
            System.out.println(user.toString());
        usersRepositoryJdbc.delete(1L);
        System.out.println("\nJDBC (Delete User with id = 1) : ");
        for (User user : usersRepositoryJdbc.findAll())
            System.out.println(user.toString());
        usersRepositoryJdbcTemplate.delete(2L);
        System.out.println("\nSpring (Delete User with id = 2) : ");
        for (User user : usersRepositoryJdbcTemplate.findAll())
            System.out.println(user.toString());

        System.out.println("\nFind User with id = 3:");
        System.out.println(usersRepositoryJdbc.findById(3L).toString());
        System.out.println("Find User with id = 4");
        System.out.println(usersRepositoryJdbcTemplate.findById(4L).toString());

        User user1 = usersRepositoryJdbc.findById(5L);
        User user2 = usersRepositoryJdbcTemplate.findById(6L);
        user1.setEmail("userUpdate");
        user2.setEmail("userUpdateTemplate");
        usersRepositoryJdbc.update(user1);
        usersRepositoryJdbcTemplate.update(user2);
        System.out.println("\nUpdate user with id = 5:");
        System.out.println(usersRepositoryJdbc.findByEmail("userUpdate").get());
        System.out.println("Update user with id = 6:");
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("userUpdateTemplate").get());
        System.err.println("CHECK SOME EXCEPTIONS");
        usersRepositoryJdbcTemplate.delete(10000L);
        usersRepositoryJdbc.delete(1000L);
        usersRepositoryJdbcTemplate.update(new User());
        User user = new User(15L, "qwe@mail.ru");
        usersRepositoryJdbc.update(user);
    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user "
                    + "(id integer not null, email varchar(20) not null);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
