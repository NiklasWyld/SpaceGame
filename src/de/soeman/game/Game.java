package de.soeman.game;

import javax.swing.*;

public class Game {
    static String title = "Soeman";
    static int game_width = 800;
    static int game_height = 600;
    static private JFrame frame;

    static private Menu menu;
    static GamePanel gamepanel;
    static private GameOver gameover_panel;

    public static void main(String[] args) {
        frame = new JFrame();

        menu = new Menu();
        gameover_panel = new GameOver();

        frame.setTitle(title);
        frame.setSize(game_width, game_height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(menu);
        frame.add(gameover_panel);

        gameover_panel.setVisible(false);
    }

    public static void startGame() {
        menu.setVisible(false);
        gameover_panel.setVisible(false);

        gamepanel = new GamePanel();
        frame.add(gamepanel);
        gamepanel.startThread();
    }

    public static void restartGame() {
        gameover_panel.setVisible(false);

        gamepanel = new GamePanel();
        frame.add(gamepanel);
        gamepanel.startThread();
    }

    public static void pauseGame() {
        gamepanel.paused = true;
        gamepanel.setVisible(false);
        menu.setVisible(true);
        menu.checkForContinueGame();
    }

    public static void coninueGame() {
        menu.setVisible(false);
        gamepanel.setVisible(true);
        gamepanel.paused = false;
    }

    public static void gameOver() {
        gamepanel.setVisible(false);
        frame.remove(gamepanel);

        gameover_panel.setVisible(true);
    }
}
