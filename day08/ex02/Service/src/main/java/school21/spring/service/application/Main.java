package school21.spring.service.application;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("school21.spring.service");
        UsersService usersService = applicationContext.getBean("userService", UsersService.class);
        UsersRepositoryJdbcImpl usersRepositoryJdbc = applicationContext.getBean("jdbcRepository", UsersRepositoryJdbcImpl.class);
        UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate = applicationContext.getBean("jdbcTemplateRepository", UsersRepositoryJdbcTemplateImpl.class);
        createTable(applicationContext);

        System.out.println("Create users: ");
        for (int i = 1; i < 10; i++)
        {
            System.out.println("User with id = " + i);
            System.out.println("Password: " + usersService.signUp("user_" + i + "_@gmail.com"));
            System.out.println(usersRepositoryJdbcTemplate.findById((long)i));
        }
        System.out.println("\nFind some users: ");
        System.out.println("User with  id = 4 :" + usersRepositoryJdbc.findById(4L));
        System.out.println("User with email = user_7_@gmail.com " + usersRepositoryJdbcTemplate.findByEmail("user_7_@gmail.com").get());
        System.out.println("\nDelete users with id 8 and 3");
        usersRepositoryJdbc.delete(8L);
        usersRepositoryJdbcTemplate.delete(3L);
        System.out.println("\nShow users after delete :");
        for(User user: usersRepositoryJdbcTemplate.findAll())
            System.out.println(user.toString());




    }

    private static void createTable(ApplicationContext applicationContext) {
        DataSource dataSource = applicationContext.getBean("hikariDataSource", HikariDataSource.class);
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
