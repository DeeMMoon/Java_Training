package printer;

import render.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer{
    private Renderer renderer;
    private String data;
    private static  final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        this.data = LocalDateTime.now().format(FORMAT);
    }

    public void print(String message) {
        renderer.print(message + " " + data);
    }
}
