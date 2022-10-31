package day01.ex05;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws UserNotFoundException, TransactionException {
        Menu menu = new Menu();
        if(args.length > 0 && args[0].equals("--profile=dev"))
            menu.run("dev_mode");
        else
            menu.run("standard_mode");
    }
}
