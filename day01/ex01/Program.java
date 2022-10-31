package day01.ex01;

import day01.ex01.User;

public class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 100);
        User john = new User("John", 200);
        User mark = new User("Mark", 300);
        mike.printData();
        john.printData();
        mark.printData();
    }
}
