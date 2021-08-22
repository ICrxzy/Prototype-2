import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected int x, y;
    protected float velX = 0, velY = 0;
    protected ID id;
    protected SpriteSheet sprite;

    public GameObject(int x, int y, ID id, SpriteSheet sprite){
        this.x = x;
        this.y = y;
        this.id = id;
        this.sprite = sprite;
    }

    public abstract void tick();
    public abstract void render(Graphics gfx);
    public abstract Rectangle getBounds();

    //getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public float getVelX() {
        return velX;
    }
    public float getVelY() {
        return velY;
    }
    public ID getId() {
        return id;
    }
}
