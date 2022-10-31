package day02.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    private static Path path;
    private static Scanner scanner;

    public static void main(String[] args) {
        checkArgs(args);
        scanner = new Scanner(System.in);
        String command = "";
        String[] commands;
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            commands = command.split("\\s+");
            if (commands.length > 3) {
                System.out.println("Too much arguments");
                continue;
            }
            try {
                switch (commands[0]) {
                    case "ls":
                        Commands.ls(path);
                        break;
                    case "cd":
                        path = Commands.cd(path, commands[1]);
                        break;
                    case "mv":
                        Commands.mv(path, commands[1], commands[2]);
                        break;
                    case "exit":
                        scanner.close();
                        break;
                    default:
                        System.out.println("Unknown command");
                }

            } catch (IOException e) {
                System.out.println("Some problems with file");
            }
        }
    }

    private static void checkArgs(String[] args)
    {
        if(args.length != 1)
        {
            System.out.println("Count of arguments more 1");
            System.exit(-1);
        }
        path = Paths.get(args[0]);
        if(args[0].startsWith("--current-folder="))
            path = Paths.get(args[0].substring(17));
        if(!Files.exists(path))
        {
            System.out.println("Invalid path");
            System.exit(-1);
        }
        System.out.println(path);
    }
}
