import java.awt.*;
import java.awt.image.BufferedImage;

public class Speed extends GameObject {
    private BufferedImage speed;

    public Speed(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
        speed = sprite.grabImage(2,3, 32, 32);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(speed, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
