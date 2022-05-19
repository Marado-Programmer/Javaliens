package pt.epcc.alunos.al220007.space;

import java.awt.*;

public class Barrier extends Rectangle {
     public Barrier(int x, int y) {
         super(x, y, 16, 16);
     }

     public void render(Graphics g) {
         g.setColor(Color.CYAN);
         g.fillRect(this.x, this.y, this.width, this.height);
     }
}
