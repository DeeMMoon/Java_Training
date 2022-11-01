package day03.ex02;

public class Summator extends Thread{
    private int start;
    private int end;
    private String name;

    public Summator(int start, int end, String name)
    {
        this.end = end;
        this.start = start;
        this.name = name;
    }

    @Override
    public void run() {
        print(name, start, end);
    }

    public static synchronized void print (String name, int startArray, int endArray)
    {
        int localSum = 0;
        for (int i = startArray; i < endArray; i++)
        {
            localSum += Program.array[i];
        }
        Program.sum += localSum;
        System.out.println("Thread " + name + ": " + "from: " + startArray + " to " + (endArray - 1) + " sum is " + localSum);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getThreadName() {
        return name;
    }

    public void setThreadName(String name) {
        this.name = name;
    }
}
