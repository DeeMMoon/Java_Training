package day01.ex04;

public class UsersArrayList implements UserList {

    private Integer capacity = defaultCapacity;

    private Integer size = 0;

    User[] users = new User[capacity];

    @Override
    public void addUser(User user) throws NullPointerException{
        if (size == capacity - 1)
            changeArraySize();
        if (user != null)
        {
            users[size] = user;
            size++;
        } else {
            throw new NullPointerException();
        }
    }

    private void changeArraySize()
    {
        capacity = capacity * 2;
        User[] tmp = new User[capacity];
        for (int i = 0; i <= size; i++)
        {
            tmp[i] = users[i];
        }
        users = tmp;
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < size; i++)
        {
            if(users[i].getId() == id)
                return (users[i]);
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByIndex(Integer index) throws UserNotFoundException, ArrayIndexOutOfBoundsException{
       if (index < 0)
           throw new ArrayIndexOutOfBoundsException();
       if (users[index] == null)
           throw new UserNotFoundException();
       return (users[index]);
    }

    @Override
    public Integer getNumberOfUsers() {
        return size;
    }
}
