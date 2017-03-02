package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
public class Main {
    public static void main(String[] args) {

        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = robot.createScreenCapture(screenRect);

	// write your code here
    }


}
