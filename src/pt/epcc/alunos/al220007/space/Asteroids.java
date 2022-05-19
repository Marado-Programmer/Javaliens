package pt.epcc.alunos.al220007.space;

import pt.epcc.alunos.al220007.game.Game;

import java.awt.*;
import java.util.Random;

public class Asteroids extends Rectangle{
    int target, initX;
    double velocity;

    double m, b;

    public Asteroids() {
        Random r = new Random();

        this.width = 16;
        this.height = 16;

        this.x = this.initX = r.nextInt(Game.frameWidth);
        this.y = 0;
        this.target = r.nextInt(Game.frameWidth);

        this.velocity = r.nextDouble(Game.frameHeight / Game.fps + 1);
        this.velocity = 1;

        this.m = (0 - Game.frameHeight) / (initX - target);

        this.b = -(m*initX);
        System.out.println(this.m);
        System.out.println(this.b);
    }

    public void update() {
        this.y += this.velocity;

        this.x += (int) (this.m * this.velocity + this.b);
        System.out.println(this.x);
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(this.x - this.width / 2, this.y - this.width / 2, this.width, this.height);
        g.setColor(Color.PINK);
        g.fillOval(this.target - this.width / 2, Game.frameHeight - this.height / 2, this.width, this.height);
    }
}
