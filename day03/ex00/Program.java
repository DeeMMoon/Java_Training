package day03.ex00;

public class Program {
    public static void main(String[] args) {
        if(args.length != 1 || !args[0].startsWith("--count="))
        {
            System.out.println("Wrong start parameters");
            System.exit(-1);
        }
        String countStr= args[0].substring(8);
        int count = Integer.parseInt(countStr);

        Hen hen = new Hen(count);
        Egg egg = new Egg(count);
        hen.start();
        egg.start();
        hen.interrupt();
        egg.interrupt();
        try {
            hen.join();
            egg.join();
        } catch (InterruptedException e) {
            System.out.println("Thread can't stop");
        }
        for (int i = 0; i < count; i++)
            System.out.println("Human");
    }
}
