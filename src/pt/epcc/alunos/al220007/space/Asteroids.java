package pt.epcc.alunos.al220007.space;

import pt.epcc.alunos.al220007.game.Game;

import java.awt.*;
import java.util.Random;

public class Asteroids extends Rectangle{
    int target, initX;
    double speed;
    double trueY;

    double m, b;

    public Asteroids() {
        Random r = new Random();

        this.width = 8;
        this.height = 8;

        this.x = this.initX = r.nextInt(Game.frameWidth);
        this.y = 0;
        this.trueY = 0;

        this.target = r.nextInt(Game.frameWidth);

        this.speed = r.nextDouble((Game.frameHeight / (Game.ups)) / 7) + 1;

        this.m = this.target - this.initX;
        this.m /= Game.frameHeight; // (y1 - y2) / (x1 - x2)

        this.b = this.initX; // y = mx + b <=> initX = m*0 + b <=> initX = b
    }

    public void update() {
        if (this.y <= Game.frameHeight - this.height) {
            this.trueY += this.speed;
            this.y = (int) Math.round(this.trueY);

            this.x = (int) (this.m * this.y + this.b);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(this.x - this.width / 2, this.y - this.width / 2, this.width, this.height);
        g.setColor(Color.RED);
        g.fillRect(this.target, Game.frameHeight - 1, 1, 1);
        g.fillRect(this.initX, 0, 1, 1);
    }
}
