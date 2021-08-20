import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> obj = new LinkedList<GameObject>(); //Creates a list of Objects
    private boolean up = false, down = false, right = false, left = false; //Is the player moving in a direction?

    public void tick(){
        for(int i = 0; i < obj.size(); i++){
            GameObject tempObj = obj.get(i);
            tempObj.tick();
        }
    }
    public void render(Graphics gfx){
        for(int i = 0; i < obj.size(); i++){
            GameObject tempObj = obj.get(i);
            tempObj.render(gfx);
        }
    }

    public void addObject(GameObject object){
        obj.add(object);
    }
    public void removeObject(GameObject object){
        obj.remove(object);
    }

    //Getters and Setters
    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean isDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
}
