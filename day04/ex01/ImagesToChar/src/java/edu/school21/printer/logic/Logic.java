package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {

    private char symbolFirst;
    private char symbolSecond;
    private BufferedImage image;
    public Logic(char symbolFirst, char symbolSecond, String path) throws IOException {
        this.symbolFirst = symbolFirst;
        this.symbolSecond = symbolSecond;
        this.image = ImageIO.read(Logic.class.getResource(path));
    }

    public void printImage()
    {
        int height = image.getHeight();
        int width = image.getWidth();
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int pix = image.getRGB(j, i);
                if(pix == Color.BLACK.getRGB())
                    System.out.print(symbolSecond);
                else if(pix == Color.WHITE.getRGB())
                    System.out.print(symbolFirst);
            }
            System.out.println();
        }
    }


}
