package day00.ex04;

import java.util.Scanner;

public class Program {
    private static final int CHARS = 65535;
    private static final int TOP_CHARS = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        char [] strArr = line.toCharArray();
        int [] intArr = getIntArray(strArr);
        char [] charArr = getCharArray(intArr);
        printStatistic(charArr, intArr);
    }
    private static int[] getIntArray(char [] strArr)
    {
        int [] result = new int[CHARS];
        for (int i = 0; i < strArr.length; i++)
        {
            result[strArr[i]] ++;
            if (result[strArr[i]] > 999)
                result[strArr[i]] = 999;
        }
        return (result);
    }
    private static char [] getCharArray(int [] intArr)
    {
        char [] charArr = new char[TOP_CHARS];
        int charVal = 0;
        for(int i = 0; i < CHARS; i++)
        {
           charVal = intArr[i];
           if(charVal > 0)
           {
               for (int j = 0; j < TOP_CHARS; j++)
               {
                  if(charVal > intArr[charArr[j]])
                  {
                    charArr = changeArr(charArr, (char)i, j);
                    break;
                  }
               }
           }
        }
        return (charArr);
    }

    private static char [] changeArr(char charArr[], char c, int index)
    {
        char [] tmp = new char[TOP_CHARS];
        for (int i = 0; i < index; i++)
        {
            tmp[i] = charArr[i];
        }
        tmp[index] = c;
        for (int i = index + 1; i < TOP_CHARS; i++)
        {
            tmp[i] = charArr[i - 1];
        }
        return (tmp);
    }
    private static void printStatistic(char[] charArr, int[] intArr)
    {
        int maxHeight = intArr[charArr[0]];
        int lines = 12;
        int[] curHigh = new int[TOP_CHARS];
        for (int i = 0; i < TOP_CHARS; i++)
        {
            curHigh[i] = intArr[charArr[i]] * 10 / maxHeight;
        }
        System.out.println();
        for (int i = 0; i < lines; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (i + curHigh[j] + 2 == lines && intArr[charArr[j]] != 0)
                    System.out.printf("%4d", intArr[charArr[j]]);
                else if (i == lines - 1 && intArr[charArr[j]] != 0)
                    System.out.printf("%4c", charArr[j]);
                else if (i + curHigh[j] >= TOP_CHARS && curHigh[j] != 0)
                    System.out.printf("%4c", '#');
                else
                    System.out.printf("%4c", ' ');
            }
            System.out.println();
        }
    }
}

