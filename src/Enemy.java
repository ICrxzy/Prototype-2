import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
    private final Handler handler;
    private final HUD hud;
    private final BufferedImage enemy;
    private final Game game;

    private final Random jenny = new Random();
    private int enemyHealth = 100;

    public Enemy(int x, int y, ID id, Handler handler, HUD hud, SpriteSheet sprite, Game game) {
        super(x, y, id, sprite);
        this.handler = handler;
        this.hud = hud;
        this.game = game;

        //enemySprites = new SpriteSheet(enemySprites);
        enemy = sprite.grabImage(3, 1, 32, 32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        for(int i = 0; i < handler.obj.size(); i++) {
            GameObject tempObject = handler.obj.get(i);
            if(tempObject.getId() == ID.Block) {
                if (getBoundsBig().intersects(tempObject.getBounds())) {
                    velX = -velX;
                    velY = -velY;
                }
                velX = (jenny.nextInt(4 - -4) + -4);
                velY = (jenny.nextInt(4 - -4) + -4);
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
            game.level1EnemyCount--;
        }
    }
    @Override
    public void render(Graphics gfx) {
        gfx.drawImage(enemy, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}
