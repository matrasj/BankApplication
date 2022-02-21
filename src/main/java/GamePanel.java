import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private final static int SCREEN_WIDTH = 600;
    private final static int SCREEN_HEIGHT = 600;
    private final static int GAME_UNIT = 25;
    private final static int UNITS = (SCREEN_HEIGHT * SCREEN_WIDTH) / GAME_UNIT;
    private final int[] x = new int[UNITS];
    private final int[] y = new int[UNITS];
    private int bodyParts = 6;
    private int applesEaten = 0;
    private int appleX;
    private char director = 'R';
    private int appleY;
    private final static int DELAY = 75;
    private boolean running = false;
    private Random random;
    private Timer timer;
    private JLabel applesLabel = new JLabel("0");
    private ExitButton exitButton = new ExitButton();


    public GamePanel(Customer customer, Data data) {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);

        applesLabel.setBounds(200, 0, 200, 100);
        applesLabel.setForeground(Color.RED);
        applesLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        this.add(applesLabel);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        exitButton.setText("Back");
        exitButton.addActionListener(this::actionPerformed);
        exitButton.setBounds(500, 0, 100, 100);
        exitButton.setHorizontalAlignment(SwingConstants.RIGHT);
        exitButton.setVerticalAlignment(SwingConstants.TOP);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        this.add(exitButton);
        startGame();
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    private void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
//        for (int i=0; i<SCREEN_WIDTH/GAME_UNIT; i++) {
//            g.drawLine(i * GAME_UNIT, 0, i * GAME_UNIT, SCREEN_HEIGHT);
//            g.drawLine(0, i * GAME_UNIT, SCREEN_WIDTH, i * GAME_UNIT);
//        }
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, GAME_UNIT, GAME_UNIT);
        g.setColor(Color.GREEN);
        for (int i=bodyParts; i>0; i--) {
            g.fillRect(x[i], y[i], GAME_UNIT, GAME_UNIT);
        }
        g.setColor(Color.CYAN);
        g.fillRect(x[0], y[0], GAME_UNIT, GAME_UNIT);

    }

    public void move() {
        for (int i=bodyParts; i>0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (director) {
            case 'R' -> {
                x[0] = x[0] + GAME_UNIT;
            }

            case 'L' -> {
                x[0] = x[0] - GAME_UNIT;
            }

            case 'D' -> {
                y[0] = y[0] + GAME_UNIT;
            }

            case 'T' -> {
                y[0] = y[0] - GAME_UNIT;
            }
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            applesEaten++;
            bodyParts++;
            newApple();
            applesLabel.setText(String.valueOf(applesEaten));
        }
    }

    public void checkCollision() {
        for (int i=bodyParts; i>0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        if (x[0] < 0 || x[0] > SCREEN_WIDTH) {
            running = false;
        }

        if (y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            running = false;
        }
    }

    public void gameOver(Graphics g) {

    }

    public void newApple() {
        appleY = random.nextInt(SCREEN_HEIGHT/GAME_UNIT) * GAME_UNIT;
        appleX = random.nextInt(SCREEN_WIDTH/GAME_UNIT) * GAME_UNIT;
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (director != 'R') {
                        director = 'L';
                    }
                }

                case KeyEvent.VK_RIGHT -> {
                    if (director != 'L') {
                        director = 'R';
                    }
                }

                case KeyEvent.VK_UP -> {
                    if (director != 'D') {
                        director = 'T';
                    }
                }

                case KeyEvent.VK_DOWN -> {
                    if (director != 'T') {
                        director = 'D';
                    }
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollision();
            checkApple();
        }
        repaint();


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.RED);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(exitButton)) {
            exitButton.setForeground(Color.WHITE);
        }
    }
}
