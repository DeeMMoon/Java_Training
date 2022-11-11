package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Optional;
import java.util.UUID;

@Component("userService")
public class UsersServiceImpl implements UsersService{

    private UsersRepository usersRepository;
    private static Long id = 0L;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplateRepository") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String signUp(String email) {
        UUID uuid = UUID.randomUUID();
        if (email == "" || email == null) {
            System.err.println("Wrong email");
            return null;
        }
        else
        {
            usersRepository.save(new User(++id, email, uuid.toString()));
            return uuid.toString();
        }
    }
}
