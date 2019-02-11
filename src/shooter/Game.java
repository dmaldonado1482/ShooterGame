package shooter;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game() {
        new Window(1200, 700, "Shooter Game", this);
        start();

        handler = new Handler();
        handler.addObject(new Player(32, 32));
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Notch game loop
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    // updates everything in the game
    public void tick() {
        handler.tick();
    }

    // renders everything inside the game
    public void render() {
        // loading frames before being shown to screen
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);   //loads 3 frames before the shown frame
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////
        g.setColor(Color.red);
        g.fillRect(0, 0, 1200, 700);

        handler.render(g);
        //////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}
