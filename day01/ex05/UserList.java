package day01.ex05;

public interface UserList {

    Integer defaultCapacity = 10;
    void addUser(User user) throws NullPointerException;
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByIndex(Integer index) throws UserNotFoundException, ArrayIndexOutOfBoundsException;
    Integer getNumberOfUsers();
}
