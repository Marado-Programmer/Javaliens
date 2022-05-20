package pt.epcc.alunos.al220007.space;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.epcc.alunos.al220007.game.Game;

public class Space {
    public List<Asteroids> asteroids = new ArrayList<Asteroids>();

    public void update() {
        asteroids.forEach(asteroid -> asteroid.update());
    }

    public void render(Graphics g) {
        asteroids.forEach(asteroid -> asteroid.render(g));
    }

    public void updatePerSecond() {
        if (Math.random() < 1 / Math.pow(2, 3)) {
            asteroids.add(new Asteroids());
        }
    }
}
