package day00.ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String week = scanner.nextLine();
        long allWeeks = 0;
        int i = 0;
        int value = 0;
        while (i++ < 18 && !week.equals("42"))
        {
            if(!week.equals("Week " + i))
                System.exit(err());
            allWeeks = getAllWeeks(getNumber(scanner), allWeeks, i);
            week = scanner.nextLine();
        }
        for(int j = 1; j < i; j++)
        {
            value = getValue(j, allWeeks);
            System.out.print("Week " + j + " ");
            while (value > 0)
            {
                System.out.print("=");
                value--;
            }
            System.out.println(">");
        }

    }
    private static int err()
    {
        System.err.println("IllegalArgument");
        return (-1);
    }

    private static int getNumber(Scanner scanner)
    {
        int number = scanner.nextInt();
        if (number < 1 || number > 9)
            System.exit(err());
        int result = number;
        for(int i = 0; i < 4; i++)
        {
            number = scanner.nextInt();
            if (number < 1 || number > 9)
                System.exit(err());
            if (result > number)
                result = number;
        }
        scanner.nextLine();
        return (result);
    }

    private static long getAllWeeks(int min, long weeks, int week)
    {
        long tmp = 1;
        for (int i = 1; i < week; i++)
            tmp *= 10;
        weeks += tmp * min;
        return (weeks);
    }

    private static int getValue(int week, long weeks)
    {
        int result = 0;
        for (int i = 1; i < week; i++)
            weeks /= 10;
        result = (int) (weeks % 10);
        return result;
    }
}
