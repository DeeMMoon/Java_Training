package day03.ex02;

public class Summator extends Thread{
    private int start;
    private int end;

    public Summator (int start, int end)
    {
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        int localSum = 0;
       for (int i = start; i < end; i++)
       {
           localSum += Program.array[i];
       }
       Program.sum += localSum;
       System.out.println("Thread " + Summator.currentThread().getName() + ": " + "from: " + start + " to " + end + " sum is " + localSum);
    }
}
