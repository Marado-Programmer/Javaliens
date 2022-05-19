package pt.epcc.alunos.al220007.space;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.epcc.alunos.al220007.game.Game;

public class Space {
    public List<Asteroids> asteroids = new ArrayList<Asteroids>();

    public Space() {
        for (int i = 0; i < 5; i++) asteroids.add(new Asteroids());
    }

    public void update() {
        asteroids.forEach(asteroid -> {
            asteroid.update();
            if (asteroid.y >= Game.frameHeight)
                asteroids.remove(asteroid);
        });
    }

    public void render(Graphics g) {
        asteroids.forEach(asteroid -> asteroid.render(g));
    }
}
