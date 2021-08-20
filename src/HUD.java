import java.awt.*;

public class HUD {
    private int health = 100, score = 100, color;
    private Game game; //used later

    public void tick(){
        health = clamp(health, 0, 100);
        color = clamp(color, 0, 255);
        color = health * 2;

        if(score <= 0) score = 0;
        if(health <= 0) {
            health = 0;
        }
    }
    public void render(Graphics gfx) {
        //Player 1 Stats
        gfx.setColor(Color.gray);
        gfx.fillRect(15, 15, 200, 32);
        gfx.setColor(new Color(100, health, 0));
        gfx.fillRect(15, 15, health * 2, 32);
        gfx.setColor(Color.black);
        gfx.drawRect(15, 15, 200, 32);

        gfx.setColor(Color.white);
        gfx.drawString("Score: " + score, 15, 64);//Player 1 Score
    }
    public static int clamp(int var, int min, int max) {
        if (var >= max) //this is basically min max checking but the easy way
            return max;
        else return Math.max(var, min);
    }
}
