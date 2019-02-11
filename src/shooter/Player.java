package shooter;

import java.awt.*;

public class Player extends GameObject {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(32, 32, 32, 32);

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
