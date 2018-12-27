package bouncingball;

import javax.swing.JFrame;

public class Main extends JFrame {
    private final Ball ball;
    private final BallDisplay ballDisplay;
    private final BallPresenter ballPresenter;

    public static void main(String[] args) {
        new Main().launch();
    }

    public Main() {
        ball = new Ball(0.95);
        ballDisplay = new BallDisplay();
        ballPresenter = new BallPresenter(ball, ballDisplay);
        this.init();
    }
    
    private void launch() {
        this.setVisible(true);
    }

    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Bouncing Ball");
        this.add(ballDisplay);
    }
    
}
