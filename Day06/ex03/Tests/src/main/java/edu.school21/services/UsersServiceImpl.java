package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import java.util.Objects;

public class UsersServiceImpl {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) throws EntityNotFoundException, AlreadyAuthenticatedException {
        User user = usersRepository.findByLogin(login);
        if(Objects.isNull(user))
            throw new EntityNotFoundException();
        if (user.isAuthenticated())
            throw new AlreadyAuthenticatedException();
        if(user.getPassword().equals(password))
        {
            user.setAuthenticated(true);
            usersRepository.update(user);
            return true;
        }
        return false;
    }

}
