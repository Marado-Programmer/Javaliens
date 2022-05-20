package pt.epcc.alunos.al220007.entities;

import pt.epcc.alunos.al220007.game.Game;
import pt.epcc.alunos.al220007.game.SpriteSheet;

import java.awt.*;

public class Missil extends Rectangle{

    public static int imageWidth = 32;

    public Missil(int shooterX, int shooterY, int[] point1, int[] point2) {
        this.x = shooterX + Missil.imageWidth / 2;
        this.y = shooterY - Missil.imageWidth;
    }

    public void update() {
        this.y -= Game.frameHeight / 9;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(this.x + Missil.imageWidth / 2 - 1, this.y, Missil.imageWidth / (Missil.imageWidth / 4), Missil.imageWidth);
        //g.drawImage(SpriteSheet.missiel, this.x, this.y, 32, 32, null);
    }
}
