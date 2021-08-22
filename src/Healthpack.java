import java.awt.*;

public class Healthpack extends GameObject {
    public Healthpack(int x, int y, ID id, SpriteSheet sprite) {
        super(x, y, id, sprite);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.gray);
        gfx.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}


