package pt.epcc.alunos.al220007.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import pt.epcc.alunos.al220007.entities.players.*;
import pt.epcc.alunos.al220007.space.*;

public class Game extends Canvas implements Runnable, MouseWheelListener {
    public static int frameWidth = 1080, frameHeight = 720;

    private boolean isRunning = false;

    public static float fps = 24, ups = 24;

    private Player player;
    private Space space;

    public Game() {
        this.addMouseWheelListener(this);

        this.setPreferredSize(new Dimension(
                this.frameWidth,
                this.frameHeight
        ));

        this.space = new Space();
        this.player = new Human(this.frameWidth / 2, this.frameHeight - 32);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.frameWidth, this.frameHeight);

        this.space.render(g);
        this.player.render(g);

        bs.show();
    }

    private void update() {
        space.update();
        player.update();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Javaliens");

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        this.isRunning = true;

        long initTime = System.nanoTime();

        final double UPDATES_PER_NANOSECOND = Math.pow(10, 9) / this.ups, FRAMES_PER_NANOSECOND = Math.pow(10, 9) / this.fps;

        double delta_ups = 0, delta_fps = 0;

        int updates = 0, frames = 0;

        long timer = System.currentTimeMillis();

        while(isRunning) {
            long curTime = System.nanoTime();

            delta_ups += (curTime - initTime) / UPDATES_PER_NANOSECOND;
            delta_fps += (curTime - initTime) / FRAMES_PER_NANOSECOND;

            initTime = curTime;

            if(delta_ups >= 1){
                update();
                updates++;
                delta_ups--;
            }

            if(delta_fps >= 1){
                render();
                frames++;
                delta_fps--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                ups = updates;
                fps = frames;
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }
    }

    /**
     * Invoked when the mouse wheel is rotated.
     *
     * @param e the event to be processed
     * @see MouseWheelEvent
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.player.plusMeters(e.getWheelRotation());
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }
}
