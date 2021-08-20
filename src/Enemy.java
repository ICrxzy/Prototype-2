import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {
    private final Handler handler;
    private HUD hud;

    private final Random jenny = new Random();
    private int enemyHealth = 100;

    public Enemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        int choice = jenny.nextInt(10);

        for(int i = 0; i < handler.obj.size(); i++) {
            GameObject tempObject = handler.obj.get(i);
            if(tempObject.getId() == ID.Block) {
                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    velX = 0;
                    velY = 0;
                }
                else if(choice == 0){
                    velX = (jenny.nextInt(4 - -4) + -4);
                    velY = (jenny.nextInt(4 - -4) + -4);
                }
            }
            if(tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    enemyHealth -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }
        if(enemyHealth <= 0) {
            hud.score += 100;
            handler.removeObject(this);
        }
    }
    @Override
    public void render(Graphics gfx) {
        gfx.setColor(Color.red);
        gfx.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}
