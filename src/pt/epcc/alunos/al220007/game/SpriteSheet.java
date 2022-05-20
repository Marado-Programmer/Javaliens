package pt.epcc.alunos.al220007.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    public static BufferedImage spriteSheet;

    public static BufferedImage missiel;

    public SpriteSheet() {
        try {
            SpriteSheet.spriteSheet = ImageIO.read(getClass().getResource("/bala.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.missiel = SpriteSheet.getSpriteSheet(0, 0, 16, 16);
    }

    public static BufferedImage getSpriteSheet(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
