import java.awt.*;

public class Ammo extends GameObject {
    public Ammo(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.pink);
        gfx.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, 32, 32);
    }
}
