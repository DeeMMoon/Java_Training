package day02.ex01;

import java.io.*;
import java.util.ArrayList;

public class Program {
    private static ArrayList<String> dictionary = new ArrayList<>();
    private static double result;
    public static void main(String[] args) {
        if (args.length != 2)
        {
            System.out.println("Wrong count of files");
            System.exit(-1);
        }
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        if (file1.isDirectory() || file2.isDirectory())
        {
            System.out.println("Parameters are directory");
            System.exit(-1);
        }
        if (file1.length()/(1024 * 1024) > 10 || file2.length()/(1024 * 1024) > 10)
        {
            System.out.println("Files too large");
            System.exit(-1);
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt")))
        {
            BufferedReader readerTextOne = new BufferedReader(new FileReader(args[0]));
            BufferedReader readerTextTwo = new BufferedReader(new FileReader(args[1]));
            addWordsToDictionary(readerTextOne);
            addWordsToDictionary(readerTextTwo);
            int [] textOne = new int[dictionary.size()];
            int [] textTwo = new int[dictionary.size()];
            readerTextOne = new BufferedReader(new FileReader(args[0]));
            readerTextTwo = new BufferedReader(new FileReader(args[1]));
            countsUniqueWordsInText(textOne, readerTextOne);
            countsUniqueWordsInText(textTwo, readerTextTwo);
            double a = 0;
            double b = 0;
            double c = 0;
            for (int i = 0; i < textOne.length; i++)
            {
                a += textOne[i] * textTwo[i];
                b += textOne[i] * textOne[i];
                c += textTwo[i] * textTwo[i];
            }
            if (b < 1 || c < 1)
                result = 0;
            else
                result = Math.floor((a / (Math.sqrt(b) * Math.sqrt(c)))*100.0)/100.0;
            System.out.println("Similarity = " + result);
            for (int i = 0; i < dictionary.size(); i++)
            {
                writer.write(dictionary.get(i));
                writer.newLine();;
            }
            readerTextOne.close();
            readerTextTwo.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void addWordsToDictionary(BufferedReader reader) throws IOException {
        String str = null;
        String [] words;
        while ((str = reader.readLine()) != null)
        {
            words = str.replaceAll("\\p{Punct}", "").toLowerCase().split("\\s+");
            for (int i = 0; i < words.length; i++)
            {
                if(!dictionary.contains(words[i]) && !words[i].isEmpty())
                    dictionary.add(words[i]);
            }
        }
        reader.close();
    }

    private static void countsUniqueWordsInText(int [] text, BufferedReader reader) throws IOException {
        String str = null;
        String [] words;
        int i;
        while ((str = reader.readLine()) != null)
        {
            words = str.replaceAll("\\p{Punct}", "").toLowerCase().split("\\s+");
            for (String word : words)
            {
                if(!word.isEmpty())
                {
                    i = dictionary.indexOf(word);
                    text[i] ++;
                }
            }
        }
    }
}
