package de.niklaswild.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {
    public GameOver() {
        setBackground(Color.black);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);
        setupUI();
    }

    public void setupUI() {
        JLabel gameover_msg = new JLabel("GAME OVER");
        gameover_msg.setFont(new Font("Arial", Font.PLAIN, 50));
        gameover_msg.setForeground(Color.red);
        gameover_msg.setBounds(250, 150, 400, 100);

        JButton try_again = new JButton("Try again");
        try_again.setBackground(Color.black);
        try_again.setForeground(Color.white);
        try_again.setBounds(350, 350, 100, 40);

        try_again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.restartGame();
            }
        });

        this.add(gameover_msg);
        this.add(try_again);
    }
}
