package printer;

import render.Renderer;

public class PrinterWithPrefixImpl implements Printer{
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void print(String message) {
        renderer.print(prefix + " " + message);
    }
}
