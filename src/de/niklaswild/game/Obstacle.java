package de.niklaswild.game;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;

public class Obstacle extends Entity {
    GamePanel panel;
    Shape obstacle;
    Player player;

    public Obstacle(GamePanel panel, Player player) {
        this.panel = panel;
        this.player = player;

        x = 750;
        y = ThreadLocalRandom.current().nextInt(1, 550 + 1);

        size = 32;
        speed = 2;
    }

    public boolean checkForCollision(Shape a, Shape b) {
        Area areaA = new Area(a);
        areaA.intersect(new Area(b));
        return !areaA.isEmpty();
    }

    public void update() {
        if (x <= 0) {
           panel.createNewObstacle();
        }

        x -= speed;

        obstacle = new Ellipse2D.Double(x, y, size, size);

        if(checkForCollision(player.shape, obstacle)) {
            Game.gameOver();
        }
    }

    public void draw(Graphics2D g) {
        obstacle = new Ellipse2D.Double(x, y, size, size);
        g.setPaint(Color.red);
        g.fill(obstacle);
        g.draw(obstacle);
    }
}
