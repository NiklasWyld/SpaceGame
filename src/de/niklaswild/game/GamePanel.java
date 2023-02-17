package de.niklaswild.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    Thread thread;

    boolean paused = false;

    Player player;
    Obstacle obstacle1;
    Obstacle obstacle2;
    Obstacle obstacle3;
    de.niklaswild.game.Point point;

    BufferedImage background_image;

    KeyHandler kh = new KeyHandler();
    int fps = 60;

    int score = 0;
    JLabel score_display;

    public GamePanel() {
        setBackground(Color.black);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);

        setupPlayer();
        setup();

        addKeyListener(kh);
        setFocusable(true);
    }

    private void setup() {
        try {
            background_image = ImageIO.read(new FileInputStream("./images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        score_display = new JLabel("Score: " + score);
        score_display.setFont(new Font("Arial", Font.BOLD, 13));
        score_display.setForeground(Color.white);
        score_display.setBounds(10, 10, 100, 15);

        this.add(score_display);
    }

    private void setupPlayer() {
        player = new Player(this, kh);
        player.x = 400;
        player.y = 300;
        player.speed = 7;
        player.size = 50;
        obstacle1 = new Obstacle(this, player);
        obstacle2 = new Obstacle(this, player);
        obstacle3 = new Obstacle(this, player);
        point = new de.niklaswild.game.Point(this, player);
    }

    public void addPoint() {
        score++;
        score_display.setText("Score: " + score);
    }

    public void createNewObstacle() {
        obstacle1 = new Obstacle(this, player);
        obstacle2 = new Obstacle(this, player);
        obstacle3 = new Obstacle(this, player);
    }

    public void createNewPoint() {
        point = new Point(this, player);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double draw_interval = 1000000000 / fps;
        double next_draw_update = System.nanoTime() + draw_interval;

        while (thread != null) {
            if (!paused) {
                update();
                repaint();
            }
            if(paused) {
                kh.up_pressed = false;
                kh.down_pressed = false;
                kh.left_pressed = false;
                kh.right_pressed = false;
            }

            try {
                double remaining_time = next_draw_update - System.nanoTime();
                remaining_time = remaining_time / 1000000;

                if (remaining_time < 0) {
                    remaining_time = 0;
                }

                Thread.sleep((long) remaining_time);

                next_draw_update += draw_interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        grabFocus();
        player.update();
        obstacle1.update();
        obstacle2.update();
        obstacle3.update();
        point.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background_image, 0, 0, 800, 600, null);

        player.draw((Graphics2D) g);
        obstacle1.draw((Graphics2D) g);
        obstacle2.draw((Graphics2D) g);
        obstacle3.draw((Graphics2D) g);
        point.draw((Graphics2D) g);
    }
}
