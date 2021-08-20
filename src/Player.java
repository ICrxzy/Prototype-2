import java.awt.*;

public class Player extends GameObject {
    private final Handler handler;
    private final Game game;
    private final HUD hud;

    //public int speed;

    public Player(int x, int y, ID id, Handler handler, Game game, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        this.hud = hud;
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
//                    speed += 5;
                }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        //player movement
        if(handler.isUp()) velY = -5;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 5;
        else if (!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = 5;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -5;
        else if(!handler.isRight()) velX = 0;
    }
    @Override
    public void render(Graphics gfx) {
        //Player Design
        gfx.setColor(Color.green);
        gfx.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
