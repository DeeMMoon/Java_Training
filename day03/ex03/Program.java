package day03.ex03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program {
    public static String filePath = "C:/Users/dim22/IdeaProjects/Java_piscine/src/day03/ex03/files_urls.txt";
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("Wrong start parameters");
            System.exit(-1);
        }
        int threadsCount = Integer.parseInt(args[0].substring(15));
        Downloader downloader = new Downloader();
        List<MyThread> threads = new ArrayList<>(threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            try {
                Downloader.getUrls();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threads.add(new MyThread(downloader, i + 1));
            threads.get(i).start();
        }
        }
}
