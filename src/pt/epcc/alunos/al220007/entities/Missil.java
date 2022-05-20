package pt.epcc.alunos.al220007.entities;

import pt.epcc.alunos.al220007.game.Game;
import pt.epcc.alunos.al220007.game.SpriteSheet;

import java.awt.*;

public class Missil extends Rectangle{

    public static int imageWidth = 32;

    double m, b;

    int lastX, lastY;

    public Missil(double[] point1, double[] point2) {
        this.x = (int) point1[0];
        this.y = (int) point1[1];

        this.m = (point1[0] - point2[0]) / (point1[1] - point2[1]);

        this.b = point1[0] - this.m * point1[1];
    }

    public void update() {
        this.lastY = this.y;
        this.y -= Game.frameHeight / 9;
        this.lastX = this.x;
        this.x = (int) (this.m * this.y + this.b);
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawLine(this.x, this.y, this.lastX, this.lastY);
        //g.drawImage(SpriteSheet.missiel, this.x, this.y, 32, 32, null);
    }
}
