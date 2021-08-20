import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected float velX = 0, velY = 0;
    protected ID id;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
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
