package day03.ex02;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static int [] array;
    public static int sum = 0;

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.out.println("Wrong start parameters");
            System.exit(-1);
        }
        int arraySize = Integer.parseInt(args[0].substring(12));
        int threadsCount = Integer.parseInt(args[1].substring(15));
        if (arraySize > 2000000 || threadsCount > arraySize)
        {
            System.out.println("Wrong arguments");
            System.exit(-1);
        }
        array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = (int) (Math.random() * (2000 + 1) - 1000);
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
        sum = 0;
        List<Summator> summators = new ArrayList<>(threadsCount);
        int sections = arraySize / (threadsCount - 1);
        int start = 0;
        int end = 0;
        for(int i = 0; i < threadsCount - 1; i++) {
            start = i * sections;
            end = (i + 1)*sections;
            summators.add(new Summator(start, end, Integer.toString(i)));
        }
        start = (threadsCount - 1) * sections;
        end = arraySize;
        summators.add(new Summator(start, end, Integer.toString(threadsCount - 1)));
        for (Summator summator : summators)
        {
            summator.start();
        }
        for (Summator summator : summators)
        {
            try {
                summator.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        if(lastSection != 0 ) {
//            Summator summatorEnd = new Summator();
//            summatorEnd.setStart(start);
//            summatorEnd.setEnd(arraySize);
//            summatorEnd.setName(Integer.toString(threadsCount));
//            summatorEnd.start();
//            try {
//                summatorEnd.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        System.out.println("Sum by threads: " + sum);
    }

}
