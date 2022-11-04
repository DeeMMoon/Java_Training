package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if(args.length != 2 || args[0].length() != 1 || args[1].length() != 1)
        {
            System.out.println("Wrong start parameters");
            System.exit(-1);
        }
        try {
            Logic logic = new Logic(args[0].charAt(0), args[1].charAt(0), "/resources/it.bmp");
            logic.printImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
