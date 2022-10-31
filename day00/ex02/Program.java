package day00.ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int number = scanner.nextInt();
        while (number != 42)
        {
            if(number > 1 && isPrime(getNumber(number)))
                count++;
            number = scanner.nextInt();
        }
        System.out.println("Count of coffee - request - " + count);
    }

    private static int getNumber(int number)
    {
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return (sum);
    }

    private static boolean isPrime(int number)
    {
        int s = 2;
        while (s * s <= number && number % s != 0)
            s++;
        return (s * s > number);
    }
}
