package day03.ex01;

public class Program {
    public static int eggSay = 0;

    public static void main(String[] args) {
        if(args.length != 1 || !args[0].startsWith("--count="))
        {
            System.out.println("Wrong start parameters");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].substring(8));
        Egg egg = new Egg(count);
        Hen hen = new Hen(count);
        egg.start();
        hen.start();
}
    public static synchronized void hen()
    {
        if(eggSay == 0) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        System.out.println("Hen");
        eggSay = 0;
        Program.class.notify();
    }

    public static synchronized void egg()
    {
        if(eggSay == 1) {
            try {
                Program.class.wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        System.out.println("Egg");
        eggSay = 1;
        Program.class.notify();
    }
}
