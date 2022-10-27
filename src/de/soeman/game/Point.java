package de.soeman.game;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.ThreadLocalRandom;

public class Point extends Entity {
    GamePanel panel;
    Shape obstacle;
    Player player;

    public Point(GamePanel panel, Player player) {
        this.panel = panel;
        this.player = player;

        x = 800;
        y = ThreadLocalRandom.current().nextInt(1, 550 + 1);

        size = 16;
        speed = 4;
    }

    public boolean checkForCollision(Shape a, Shape b) {
        Area areaA = new Area(a);
        areaA.intersect(new Area(b));
        return !areaA.isEmpty();
    }

    public void update() {
        if (x <= 0) {
            panel.createNewPoint();
        }

        x -= speed;

        obstacle = new Ellipse2D.Double(x, y, size, size);

        if(checkForCollision(player.shape, obstacle)) {
            panel.addPoint();
            panel.createNewPoint();
        }
    }

    public void draw(Graphics2D g) {
        obstacle = new Ellipse2D.Double(x, y, size, size);
        g.setPaint(Color.green);
        g.fill(obstacle);
        g.draw(obstacle);
    }
}
