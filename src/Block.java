import java.awt.*;

public class Block extends GameObject {
    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }
    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.blue);
        gfx.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
