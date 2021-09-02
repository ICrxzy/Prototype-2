import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private final Handler handler;
    private final Game game;
    private final HUD hud;
    private final Bullet bullet;
    private final BufferedImage player;

    private int speed = 2;

    public Player(int x, int y, ID id, Handler handler, Game game, HUD hud, SpriteSheet sprite, Bullet bullet) {
        super(x, y, id, sprite);
        this.handler = handler;
        this.game = game;
        this.hud = hud;
        this.bullet = bullet;
        player = sprite.grabImage(1, 3, 32, 32);
    }

    private void collision(){
        for(int i = 0; i < handler.obj.size(); i++) {
            GameObject tempObject = handler.obj.get(i);
            if(tempObject.getId() == ID.Block)
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            if(tempObject.getId() == ID.Crate)
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.ammo += hud.clamp(game.ammo, 2, 5);
                    if(game.ammo >= 20) game.ammo = 20;
                    handler.removeObject(tempObject);
                }
            if(tempObject.getId() == ID.Enemy)
                if (getBounds().intersects(tempObject.getBounds())) {
                    hud.health -= 1;
                }
            if(tempObject.getId() == ID.Speed)
                if (getBounds().intersects(tempObject.getBounds())) {
                    if(speed >= 8){
                        speed = 8;
                    } else {
                        speed += 2;
                    }
                    handler.removeObject(tempObject);
                }
            if(tempObject.getId() == ID.Healthpack)
                if(getBounds().intersects(tempObject.getBounds())){
                    if(hud.health >= 100) //If the user has full health when touched
                        hud.health = 100; //dont remove object and keep health at 100
                    else { //but if the user is lower than max HP
                        handler.removeObject(tempObject); //remove the healthpack
                        hud.health = hud.health + 20; //add 20 to health.
                    }
                    if(hud.health >= 100) hud.health = 100; //check if health is max or overflowing, then set it to 100.
                }
            if(tempObject.getId() == ID.FastFire)
                if(getBounds().intersects(tempObject.getBounds())){
                    if(bullet.speed >= 20)
                        bullet.speed = 20;
                    else bullet.speed += 5;
                }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        //player movement
        if(speed >= 10) speed = 8;
        if(handler.isUp()) velY = -speed;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = speed;
        else if (!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = speed;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -speed;
        else if(!handler.isRight()) velX = 0;
    }
    @Override
    public void render(Graphics gfx) {
        //Player Design
            gfx.drawImage(player, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
