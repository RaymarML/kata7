package bouncingball;

import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    private static final double g = -9.8;
    private final double e;
    private final Timer timer;
    private double y = 200;
    private double dy = 0;
    private boolean grabbed = false;
    private static final int period = 20;
    private static final double dt = 0.2;
    private final List<Observer> observers = new ArrayList<>();

    public Ball(double e) {
        this.e = e;
        this.timer = timer();
    }

    private Timer timer() {
        Timer timer = new Timer();
        timer.schedule(timerTask(), 0, period);
        return timer;
    }

    private TimerTask timerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                step(dt);
                updateObservers();
            }           

            private void updateObservers() {
                for (Observer observer : observers) {
                    observer.update(null, null);
                }
            }
        };
    }
                
    private void step(double dt) {
        if (grabbed) return;
        dy = dy + g * dt;
        y = y + dy * dt + g * dt * dt / 2.0;
        if (y > 0) return;
        dy = -dy * e;
        y = -y /2;
    }

    public double getY() {
        return y;
    }

    public boolean isGrabbed() {
        return grabbed;
    }

    public void setGrabbed(boolean grabbed) {
        this.grabbed = grabbed;
        this.dy = 0;
    }

    public void add(Observer observer) {
        observers.add(observer);
    }

    void setY(double y) {
        this.y = y;
    }
    

}
