package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Program {
    private static final String file = "signatures.txt";
    private  static Map<String, String> signature = new HashMap<>();
    public static void main(String[] args) {
        FileInputStream fileInputStream;
        Scanner scanner;
        String inputLine;
        try {
            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);
            String line;
            String [] array;
            while (scanner.hasNextLine())
            {
                line = scanner.nextLine().replaceAll("\\s+", "");
                array = line.split(",");
                signature.put(array[0],array[1]);
            }
            scanner.close();
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
        scanner = new Scanner(System.in);
        inputLine = scanner.nextLine();
        while (!inputLine.equals("42"))
        {
            checkSignature(inputLine);
            inputLine = scanner.nextLine();
        }
    }

    private static void checkSignature(String line)
    {
        byte [] bytes = new byte[8];
        String file;
        try(FileInputStream fileInputStream = new FileInputStream(line)) {
            fileInputStream.read(bytes, 0, 8);
            file = convertToHex(bytes).toUpperCase();
            System.out.println("File = "+ file);
            find(file);
        }
        catch (IOException e) {
            System.out.println("Can't read file");
            System.exit(-1);
        }

    }

    private static String convertToHex(byte [] bytes)
    {
        char [] hex = new char[bytes.length * 2];
        String str = new String();
        for (byte b: bytes)
        {
            str = str + String.format("%02x", b);
        }
        return str;
    }

    private static void find(String sig)
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true)){
            for (Map.Entry<String, String> tmp : signature.entrySet())
            {
                if(sig.contains(tmp.getValue()))
                {
                    fileOutputStream.write(tmp.getKey().getBytes());
                    fileOutputStream.write('\n');
                    System.out.println("PROCESSED");
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (IOException e) {
            System.out.println("Can't input result");
        }
    }
}
