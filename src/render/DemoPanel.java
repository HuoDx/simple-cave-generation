package render;

import terrian.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class DemoPanel extends JPanel implements ActionListener, MouseMotionListener {
    ArrayList<IRenderer> renderers;
    final int deltaTime = 30;
    public DemoPanel() {
        cameraPosition = new Vector2(0,0);
        renderers = new ArrayList<>();
        addMouseMotionListener(this);
        Timer t = new Timer(deltaTime, this);
        t.start();

    }
    public void registerRenderer(IRenderer _renderer) {
        renderers.add(_renderer);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-cameraPosition.x, -cameraPosition.y);
        renderers.forEach((r) -> r.render(g2d));
    }

    int width, height;
    Vector2 cameraPosition;

    @Override
    public void actionPerformed(ActionEvent e) {
        Point mousePosition = this.getMousePosition();
        if(mousePosition == null) return;
        //System.out.println(mousePosition);
        if(mousePosition.x > 0.6 * width) cameraPosition.x += 5 * ((double)(deltaTime) / 10);
        //cameraPosition.x = cameraPosition.x > width ? width : cameraPosition.x;
        if(mousePosition.x < 0.3 * width) cameraPosition.x -= 5 * ((double)(deltaTime) / 10) ;
        //cameraPosition.x = cameraPosition.x < 1 ? 0 : cameraPosition.x;

        if(mousePosition.y > 0.6 * height) cameraPosition.y += 5 * ((double)(deltaTime) / 10);
        //cameraPosition.y = cameraPosition.y > height ? height : cameraPosition.y;
        if(mousePosition.y < 0.3 * height) cameraPosition.y -= 5 * ((double)(deltaTime) / 10) ;
       // cameraPosition.y = cameraPosition.y < 0 ? 0 : cameraPosition.y;t
        this.repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // writing it here is to improve performance in a stupidly simple way.
        width = this.getWidth();
        height = this.getHeight();
    }
}
