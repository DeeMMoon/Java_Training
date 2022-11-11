package school21.spring.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;

@Component
public class TestApplicationConfig {

    @Bean
    DataSource dataSource()
    {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
        return dataSource;
    }

    @Bean("usersServiceJdbcTemplate")
    UsersService usersServiceJdbcTemplate(DataSource dataSource)
    {
        UsersService usersService = new UsersServiceImpl(new UsersRepositoryJdbcTemplateImpl(dataSource));
        return usersService;
    }

    @Bean("usersServiceJdbc")
    UsersService usersServiceJdbc(DataSource dataSource)
    {
        UsersService usersService = new UsersServiceImpl(new UsersRepositoryJdbcImpl(dataSource));
        return usersService;
    }
}
