package shooter;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
    ArrayList<GameObject> objects = new ArrayList<>();

    public void tick() {
        for(GameObject object : objects) {
            object.tick();
        }
    }

    public void render(Graphics g) {
        for(GameObject object : objects) {
            object.render(g);
        }
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }
}
