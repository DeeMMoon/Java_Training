package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preprocessor.PreProcessor;
import preprocessor.PreProcessorToUpperImpl;
import printer.Printer;
import printer.PrinterWithPrefixImpl;
import render.Renderer;
import render.RendererErrImpl;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithDateErrLow", Printer.class);
        printer.print ("Hello") ;
        printer = context.getBean("printerWithDateErrUp", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithPrefixErrUp", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithPrefixErrLow", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithDateStdLow", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithDateStdUp", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithPrefixStdUp", Printer.class);
        printer.print("Hello");
        printer = context.getBean("printerWithPrefixStdLow", Printer.class);
        printer.print("Hello");
    }
}
