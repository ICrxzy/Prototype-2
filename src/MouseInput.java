import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private final Handler handler;
    private final Camera camera;
    private final Game game;

    public MouseInput(Handler handler, Camera camera, Game game) {
        this.game = game;
        this.handler = handler;
        this.camera = camera;
    }
    public void mousePressed(MouseEvent e) {
        int mouseX = (int) (e.getX() + camera.getX());
        int mouseY = (int) (e.getY() + camera.getY());

        for(int i = 0; i < handler.obj.size(); i++){
            GameObject tempObject = handler.obj.get(i);
            if(tempObject.getId() == ID.Player && game.ammo >= 1) {
                handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 16, ID.Bullet, handler, mouseX, mouseY, tempObject.sprite));
                game.ammo--;
            }
        }
    }
}
