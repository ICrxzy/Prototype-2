import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Canvas implements Runnable {
    //Important Variables
    private static MainMenu menu = new MainMenu();
    private static HUD hud;
    private final Handler handler;
    private Thread thread;
    private final Camera camera;
    private boolean running = false;
    private final int width = 1200, height = 600;

    //Other Variables
    public int ammo = 100;

    public static void main(String[] args) {
        //menu.startGame(args);
        new Game();
    }

    //Handles all the major game stuff
    public Game(){
        new Window(1200, 600, "Prototype", this); //Creates the game window
        hud = new HUD();
        start(); //Starts the thread

        handler = new Handler(); //Declares handler for objects
        camera = new Camera(0, 0); //declares the camera
        this.addKeyListener(new KeyInput(handler)); //Listen for keyboard input
        this.addMouseListener(new MouseInput(handler, camera, this)); //listen for mouse input
        BufferedImageLoader loader = new BufferedImageLoader(); //Declare the image loader
        BufferedImage level = loader.loadImage("/level.png"); //declare image to load as the level

        loadLevel(level); //create the level
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double tickRate = 64.0;
        double ns = 1000000000 / tickRate;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int FPS = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            FPS++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                FPS = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    //Loads the level from image
    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < w; xx++)
            for(int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(blue == 255) handler.addObject(new Block(xx*32, yy*32, ID.Block)); //blue square on image loaded will be a block.
                if(green == 255) handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, this));
                if(red == 255) handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler));
                if(red == 255 && blue == 255) handler.addObject(new Crate(xx*16, yy*16, ID.Crate));
            }
    }

    //Game Updates
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics gfx = bs.getDrawGraphics();
        Graphics2D gfx2D = (Graphics2D) gfx;

        //////// DRAW OBJECTS HERE ////////

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, width, height);

        //healthbar
        hud.render(gfx);

        //////////////////////////////////

        gfx2D.translate(-camera.getX(), -camera.getY());
        handler.render(gfx);
        gfx2D.translate(camera.getX(), camera.getY());

        gfx.dispose();
        bs.show();
    }
    public void tick(){
        for(int i = 0; i < handler.obj.size(); i++)
            if(handler.obj.get(i).getId() == ID.Player)
                camera.tick(handler.obj.get(i));

        handler.tick();
    }

    //Thread Controlling
    private void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    private void stop() throws InterruptedException {
        running = false;
        thread.join();
    }
}
