import render.DemoPanel;
import terrian.Terrian;
import terrian.Vector2;

import javax.swing.*;
import java.awt.*;

public class CaveGeneration {

    public CaveGeneration() {
        JFrame frame = new JFrame("Cave Generation Demo.");
        frame.setBackground(Color.white);
        frame.setBounds(new Rectangle(0,0,1024,768));
        DemoPanel panel = new DemoPanel();
        panel.registerRenderer(new Terrian(Terrian.generateBetter(new Vector2(256,256))));
        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new CaveGeneration();
    }

}
