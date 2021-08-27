import java.awt.*;
import java.awt.image.BufferedImage;

public class FastFire extends GameObject {
    private final BufferedImage fastFire;

    public FastFire(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
        fastFire = sprite.grabImage(3, 2, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(fastFire, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
