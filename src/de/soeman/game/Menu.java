package de.soeman.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private static JButton continue_game;

    public Menu() {
        setBackground(Color.black);
        setSize(Game.game_width, Game.game_height);
        setLayout(null);

        setupUI();
    }

    public void setupUI() {
        JLabel title = new JLabel("Soeman");
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        title.setForeground(Color.white);
        title.setBounds(300, 100, 200, 50);

        JButton new_game = new JButton("New Game");
        new_game.setBackground(Color.black);
        new_game.setForeground(Color.white);
        new_game.setBounds(350, 300, 100, 40);

        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.startGame();
            }
        });

        continue_game = new JButton("Continue");
        continue_game.setBackground(Color.black);
        continue_game.setForeground(Color.white);
        continue_game.setBounds(350, 250, 100, 40);
        continue_game.setEnabled(false);

        continue_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.coninueGame();
            }
        });

        JButton exit_game = new JButton("Exit");
        exit_game.setBackground(Color.black);
        exit_game.setForeground(Color.white);
        exit_game.setBounds(350, 350, 100, 40);

        exit_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(title);
        this.add(new_game);
        this.add(continue_game);
        this.add(exit_game);
    }

    public void checkForContinueGame() {
        if(Game.gamepanel.paused) {
            continue_game.setEnabled(true);
        } else {
            continue_game.setEnabled(false);
        }
    }
}
