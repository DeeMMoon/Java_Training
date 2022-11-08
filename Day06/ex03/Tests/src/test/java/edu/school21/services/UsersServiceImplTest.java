package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest {
    private final UsersRepository usersRepository = mock(UsersRepository.class);
    private final UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);
    private User user;

    @BeforeEach
    public void init()
    {
        user = new User(1L, "correctLogin", "correctPassword", false);
        when(usersRepository.findByLogin("correctLogin")).thenReturn(user);
        doNothing().when(usersRepository).update(user);
    }

    @Test
    public void incorrectLoginTest() throws AlreadyAuthenticatedException, EntityNotFoundException {
        Assertions.assertThrows(EntityNotFoundException.class, ()->usersService.authenticate("incorrectLogin","correctPassword"));
        verify(usersRepository).findByLogin("incorrectLogin");
        verify(usersRepository, never()).update(user);

    }
    @Test
    public void incorrectPasswordTest() throws AlreadyAuthenticatedException, EntityNotFoundException {
        Assertions.assertFalse(usersService.authenticate("correctLogin","incorrectPassword"));
        verify(usersRepository).findByLogin("correctLogin");
        verify(usersRepository, never()).update(any());
    }
    @Test
    public void correctLoginAndPasswordTest() throws AlreadyAuthenticatedException, EntityNotFoundException {
        Assertions.assertTrue(usersService.authenticate("correctLogin", "correctPassword"));
        verify(usersRepository).findByLogin("correctLogin");
        verify(usersRepository).update(user);
    }
    @Test
    public void userIsAuthenticate()
    {
        user.setAuthenticated(true);
        usersRepository.update(user);
        Assertions.assertThrows(AlreadyAuthenticatedException.class, ()->usersService.authenticate("correctLogin", "correctPassword"));
    }
}
