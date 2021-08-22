import java.awt.*;

public class FastFire extends GameObject {
    public FastFire(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.orange);
        gfx.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
