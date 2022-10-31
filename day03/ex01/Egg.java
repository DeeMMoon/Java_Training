package day03.ex01;

public class Egg extends Thread{
    private int count;

    public Egg(int count){
        this.count = count;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++)
            Program.egg();
    }
}
