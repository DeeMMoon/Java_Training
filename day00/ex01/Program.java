package day00.ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int number = 0;
        int s = 2;
        int exitStatus = 0;
        Scanner scanner = new Scanner(System.in);
        number = scanner.nextInt();
        if (number < 2) {
            exitStatus = err();
        }
        else {
            while (s * s <= number && number % s != 0)
                s++;
            System.out.print((s * s > number) + " ");
            System.out.println(s - 1);
        }
        System.exit(exitStatus);
    }
    private static int err()
    {
        System.err.println("IllegalArgument");
        return (-1);
    }
}
