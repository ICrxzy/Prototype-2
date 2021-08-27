import java.awt.*;
import java.awt.image.BufferedImage;

public class Healthpack extends GameObject {
    private final BufferedImage healthpack;

    public Healthpack(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
        healthpack = sprite.grabImage(1, 1, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(healthpack, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}


