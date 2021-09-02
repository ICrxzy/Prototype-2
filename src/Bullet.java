import java.awt.*;

public class Bullet extends GameObject {
    private final Handler handler;
    public int speed = 10;

    public Bullet(int x, int y, ID id, Handler handler, int mouseX, int mouseY, SpriteSheet sprite) {
        super(x, y, id, sprite);
        this.handler = handler;

        velX = (float) (mouseX - x) / speed;
        velY = (float) (mouseY - y) / speed;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for(int i = 0; i < handler.obj.size(); i++) {
            GameObject tempObject = handler.obj.get(i);
            if(tempObject.getId() == ID.Block)
                if (getBounds().intersects(tempObject.getBounds()))
                    handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.YELLOW);
        gfx.fillOval(x, y, 8, 5);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 5);
    }
}
