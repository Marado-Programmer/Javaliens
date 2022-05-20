package pt.epcc.alunos.al220007.entities;

import pt.epcc.alunos.al220007.game.Game;
import pt.epcc.alunos.al220007.game.SpriteSheet;

import java.awt.*;

public class Missil extends Rectangle{

    public static int imageWidth = 32;

    public Missil(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.y -= Game.frameHeight / 21;
    }

    public void render(Graphics g) {
        g.fillOval(this.x + 32 / 2, this.y, 32 / 8, 32);
        //g.drawImage(SpriteSheet.missiel, this.x, this.y, 32, 32, null);
    }
}
