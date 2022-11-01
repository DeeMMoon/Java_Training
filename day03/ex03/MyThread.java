package day03.ex03;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MyThread extends Thread{

    private Downloader downloader;
    private int index;


    public MyThread(Downloader downloader, int index)
    {
        this.downloader = downloader;
        this.index = index;

    }
    @Override
    public void run() {
        while (!downloader.getDownload())
        {
            String str = downloader.getUrl();
            byte buffer[] = new byte[1024];
            int reader;
            String [] strings = str.split("/");
            File file = new File(strings[strings.length - 1]);
            try(BufferedInputStream inputStream  = new BufferedInputStream(new URL(str).openStream())) {
                FileOutputStream outputStream = new FileOutputStream(file);
                int indexOfFile = downloader.getKey(str);
                System.out.println("Thread-" + index + " start download file number " + indexOfFile);
                while ((reader = inputStream.read(buffer)) != -1)
                    outputStream.write(buffer, 0, reader);
                System.out.println("Thread-" + index + " finish download file number " + indexOfFile);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
