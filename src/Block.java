import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
    private BufferedImage block;
    public Block(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);

        block = sprite.grabImage(1, 1, 32, 32);
    }

    @Override
    public void tick() {

    }
    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(block, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
