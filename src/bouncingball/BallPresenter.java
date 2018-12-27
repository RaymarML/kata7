package bouncingball;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

public class BallPresenter implements Observer{
    private final Ball ball;
    private final BallDisplay ballDisplay;
    private final double MaxHeight = 300.0;

    public BallPresenter(Ball ball, BallDisplay ballDisplay) {
        this.ball = ball;
        this.ballDisplay = ballDisplay;
        this.ballDisplay.addMouseListener(mouseListener());
        this.ballDisplay.addMouseMotionListener(mouseMotionListener());
        this.ball.add(this);
    }
    
    private double toWorld(int y) {
        return MaxHeight * (ballDisplay.getHeight()-y*1.0)/ballDisplay.getHeight();
    }

    private int toScreen(double y) {
        return (int) ((MaxHeight - y)/MaxHeight * ballDisplay.getHeight());
    }

    @Override
    public void update(Observable o, Object o1) {
        refresh();
    }

    private void refresh() {
        ballDisplay.paintBall(ballDisplay.getWidth()/2, toScreen(ball.getY()+BallDisplay.Size/2));
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (isTouchingBall(me.getX(), me.getY())) 
                    ball.setGrabbed(true);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (ball.isGrabbed()) ball.setGrabbed(false);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

            private boolean isTouchingBall(int x, int y) {
                return 
                    (Math.abs(x-ballDisplay.getWidth()/2) < BallDisplay.Size) &&
                    (Math.abs(y-toScreen(ball.getY())) < BallDisplay.Size);
            }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (ball.isGrabbed()) {
                    ball.setY(toWorld(me.getY()));
                }
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        };
    }

}
