package de.soeman.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel panel;
    KeyHandler kh;
    Rectangle2D shape;

    static BufferedImage sprite;

    public Player(GamePanel panel, KeyHandler kh) {
        this.panel = panel;
        this.kh = kh;

        setupSprite();
    }

    private void setupSprite() {
        try {
            sprite = ImageIO.read(new FileInputStream("./images/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.shape = new Rectangle2D.Double(x, y, size, size);

        if (kh.up_pressed) {
            if (y >= 0) {
                y -= speed;
            }
        }
        if (kh.down_pressed) {
            if (y <= Game.game_height - size * 1.9) {
                y += speed;
            }
        }
        if (kh.left_pressed) {
            if (x >= 0) {
                x -= speed;
            }
        }
        if(kh.right_pressed) {
            if (x <= Game.game_width - size * 1.4) {
                x += speed;
            }
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite, x, y, size, size, null);
    }
}
