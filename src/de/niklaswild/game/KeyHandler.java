package de.niklaswild.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    boolean up_pressed, down_pressed, left_pressed, right_pressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            up_pressed = true;
        }
        if (e.getKeyCode() == 40) {
            down_pressed = true;
        }
        if (e.getKeyCode() == 37) {
            left_pressed = true;
        }
        if (e.getKeyCode() == 39) {
            right_pressed = true;
        }
        if (e.getKeyCode() == 27) {
            Game.pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            up_pressed = false;
        }
        if (e.getKeyCode() == 40) {
            down_pressed = false;
        }
        if (e.getKeyCode() == 37) {
            left_pressed = false;
        }
        if (e.getKeyCode() == 39) {
            right_pressed = false;
        }
    }
}
