package bouncingball;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallDisplay extends JPanel {
    public static final int Size = 20;
    private int x;
    private int y;
    
    public void paintBall(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.white);
        g.fillOval(x, y, Size, Size);
    }
    
    

}
