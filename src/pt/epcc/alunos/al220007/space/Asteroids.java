package pt.epcc.alunos.al220007.space;

import pt.epcc.alunos.al220007.game.Game;

import java.awt.*;
import java.util.Random;

public class Asteroids extends Rectangle{
    int target, initX;
    double speed;

    int count;

    double m, b;

    public Asteroids() {
        Random r = new Random();

        this.width = 16;
        this.height = 16;

        this.x = this.initX = r.nextInt(Game.frameWidth);
        this.y = 0;
        this.target = r.nextInt(Game.frameWidth);

        this.speed = r.nextDouble(Game.frameHeight / Game.fps + 1);
        this.speed = 5;

        this.m = (0 - Game.frameHeight) / (initX - target); // (y1 - y2) / (x1 - x2)

        this.b = -(m*initX); // y = mx + b <=> 0 = m*initX + b <=> -b = m*initX
        System.out.println(this.m);
        System.out.println(this.b);
    }

    public void update() {
        this.y += this.speed;
        this.count++;

        this.x += (int) this.m * this.count + this.b;
        System.out.println(this.count);
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(this.x - this.width / 2, this.y - this.width / 2, this.width, this.height);
        g.setColor(Color.PINK);
        g.fillOval(this.target - this.width / 2, Game.frameHeight - this.height / 2, this.width, this.height);
    }
}
