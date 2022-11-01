package day03.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Downloader {

   private static Map<Integer, String> urls = new HashMap<>();
   private boolean download = false;

   private int keyStatus = 1;

   public static void getUrls() throws IOException {
       List<String> list = Files.readAllLines(Paths.get(Program.filePath));
       if(list.isEmpty())
       {
           System.out.println("File with URLs is empty");
           System.exit(-1);
       }
       for(String str : list)
       {
           String [] values = str.split("\\s+");
           urls.put(Integer.parseInt(values[0]), values[1]);
       }
   }
   public synchronized boolean getDownload()
   {
       return download;
   }
   public int getKey(String str)
   {
       for (Map.Entry<Integer, String> key : urls.entrySet())
       {
           if(key.getValue().equals(str))
               return (int)key.getKey();
       }
       return (-1);
   }
   public synchronized String getUrl()
   {
       if(!urls.containsKey(keyStatus))
           return null;
       String str = urls.get(keyStatus);
       keyStatus++;
       if(!urls.containsKey(keyStatus))
           download = true;
       return str;
   }
}
