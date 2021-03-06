package pt.epcc.alunos.al220007.entities.players;

import pt.epcc.alunos.al220007.entities.Missil;
import pt.epcc.alunos.al220007.game.Game;
import pt.epcc.alunos.al220007.game.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
    private int metersToWalk = 0;

    private float speed = 0;
    private float acelaration = 5;

    private float rotation = 90;

    private double[] topPoint = new double[2];

    private List<Missil> missils = new ArrayList<>();

    public Player(int x, int y) {
        super(x, y, 64, 64);
    }

    public void update() {
        this.walk();

        if (rotation != 90)
            this.rotation += (this.rotation > 90 ? -((this.speed + this.acelaration) / this.acelaration) : (this.speed + this.acelaration) / this.acelaration);

        this.missils.forEach(missil -> missil.update());
    }

    private void walk() {
        this.calcSpeed();
        if (this.metersToWalk != 0) {
            this.x += (int) this.speed;

            this.rotation += this.speed;

            if (this.rotation > (90 + 30))
                this.rotation = 90 + 30;
            else if (this.rotation < (90 - 30))
                this.rotation = 90 - 30;

            if (this.x < 0)
                this.x = 0;
            else if (this.x > Game.frameWidth - this.width)
                this.x = Game.frameWidth - this.width;

            this.plusMeters(this.metersToWalk > 0 ? -1 : 1);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillPolygon(this.calcPolygonBasedOnRotation());

        this.missils.forEach(missil -> missil.render(g));
    }

    public void plusMeters(int meters) {
        if (this.x >= 0 && this.x < Game.frameWidth - this.width)
            this.metersToWalk += meters;
    }

    private void calcSpeed() {
        this.speed = this.acelaration * this.metersToWalk;
    }

    public void shoot() {
        this.missils.add(new Missil(
                this.topPoint,
                new double[] {this.x + this.width / 2, this.y + this.height - Math.round(this.height / 4)}
        ));
    }

    private Polygon calcPolygonBasedOnRotation() {
        double sin = Math.sin(Math.toRadians(this.rotation));
        double cos = Math.cos(Math.toRadians(this.rotation));

        int x1 = this.x - this.width / 4;
        int x2 = this.x + this.width / 4 * 5;
        int y1 = this.y;
        int y2 = y + this.width / 4 * 6;

        this.topPoint[0] = x1 + ((x2 - x1) / 2) * (1 - cos);
        this.topPoint[1] = y1 + ((y2 - y1) / 2) * (1 - sin);

        double[] leftPoint = new double[2];
        leftPoint[0] = x1 + (x2 - x1) / 6;
        leftPoint[0] += (x2 - x1) / 6 * ((sin > 0 && cos < 0) ? -cos : (sin < 0 && cos > 0) ? 1 - cos : cos < 0 ? 1 : 0);
        leftPoint[0] += (x2 - x1) / 2 * ((sin < 0 && cos < 0) ? 1 + cos : (sin > 0 && cos > 0) ? cos : cos < 0 ? 0 : 1);
        leftPoint[1] = y1 + (y2 - y1) / 6;
        leftPoint[1] += (y2 - y1) / 2 * ((sin > 0 && cos < 0) ? sin : (sin < 0 && cos > 0) ? 1 + sin : cos < 0 ? 0 : 1);
        leftPoint[1] += (y2 - y1) / 6 * ((sin < 0 && cos < 0) ? -sin : (sin > 0 && cos > 0) ? 1 - sin : cos < 0 ? 0 : 1);


        double[] rightPoint = new double[2];
        rightPoint[0] = x1 + (x2 - x1) / 6;
        rightPoint[0] += (x2 - x1) / 6 * ((sin < 0 && cos < 0) ? -cos : (sin > 0 && cos > 0) ? 1 - cos : cos < 0 ? 1 : 0);
        rightPoint[0] += (x2 - x1) / 2 * ((sin > 0 && cos < 0) ? 1 + cos : (sin < 0 && cos > 0) ? cos : cos < 0 ? 0 : 1);
        rightPoint[1] = y1 + (y2 - y1) / 6;
        rightPoint[1] += (y2 - y1) / 6 * ((sin > 0 && cos < 0) ? 1 - sin : (sin < 0 && cos > 0) ? -sin : cos < 0 ? 1 : 0);
        rightPoint[1] += (y2 - y1) / 2 * ((sin > 0 && cos > 0) ? sin : (sin < 0 && cos < 0) ? 1 + sin : cos < 0 ? 1 : 0);

        Polygon polygon = new Polygon(
                new int[] {(int) leftPoint[0], (int) this.topPoint[0], (int) rightPoint[0], this.x + this.width / 2},
                new int[] {(int) leftPoint[1], (int) this.topPoint[1], (int) rightPoint[1], this.y + this.height - Math.round(this.height / 4)},
                4);

        return polygon;
    }
}
