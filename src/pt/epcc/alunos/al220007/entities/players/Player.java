package pt.epcc.alunos.al220007.entities.players;

import java.awt.*;

public class Player extends Rectangle {
    private int metersToWalk = 0;

    private float speed = 0;
    private float acelaration = 5;

    public Player(int x, int y) {
        super(x, y, 32, 32);
    }

    public void update() {
        this.calcSpeed();

        this.walk();
    }

    private void walk() {
        if (this.metersToWalk != 0) {
            this.x += (int) this.speed;

            this.plusMeters(this.metersToWalk > 0 ? -1 : 1);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public void plusMeters(int meters) {
        this.metersToWalk += meters;
    }

    private void calcSpeed() {
        this.speed = this.acelaration * this.metersToWalk;
    }
}
