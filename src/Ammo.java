import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends GameObject {
    private final BufferedImage ammo;

    public Ammo(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);

        ammo = sprite.grabImage(2, 1, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(ammo, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y, 32, 32);
    }
}
