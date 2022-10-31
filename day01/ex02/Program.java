package day01.ex02;

public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        UsersArrayList list = new UsersArrayList();
        list.addUser(new User("user1", 10));
        list.addUser(new User("user2", 10));
        list.addUser(new User("user3", 10));
        list.addUser(new User("user4", 10));
        list.addUser(new User("user5", 10));
        list.addUser(new User("user6", 10));
        list.addUser(new User("user7", 10));
        list.addUser(new User("user8", 10));
        list.addUser(new User("user9", 10));
        list.addUser(new User("user10", 10));
        list.addUser(new User("user11", 10));
        System.out.println(list.getNumberOfUsers());
        list.getUserById(1).printData();
        list.getUserById(2).printData();
        list.getUserById(3).printData();
        list.getUserById(4).printData();
        list.getUserById(11).printData();
        list.getUserByIndex(6).printData();
        list.getUserByIndex(7).printData();
        list.getUserByIndex(8).printData();
        list.getUserByIndex(9).printData();
        list.getUserByIndex(10).printData();
        System.out.println("EXCEPTION");
    }
}
