package school21.spring.service.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersServiceImplTest {

    private static DataSource dataSource;
    private static UsersService usersServiceJdbc;

    private static UsersService usersServiceJdbcTemplate;

    @BeforeAll
    static void init()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        dataSource = context.getBean("dataSource", DataSource.class);
        usersServiceJdbc = context.getBean("usersServiceJdbc", UsersService.class);
        usersServiceJdbcTemplate = context.getBean("usersServiceJdbcTemplate", UsersService.class);
    }

    @BeforeEach
    void createTable(){
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

    @ParameterizedTest
    @ValueSource(strings = {"user_1_@gmail.com", "user_2_@gmail.com", "user_3_@gmail.com"})
    void test1(String login)
    {
        Assertions.assertNotNull(usersServiceJdbc.signUp(login));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user_4_@gmail.com", "user_5_@gmail.com", "user_6_@gmail.com"})
    void test2(String login)
    {
        Assertions.assertNotNull(usersServiceJdbcTemplate.signUp(login));
    }

}
