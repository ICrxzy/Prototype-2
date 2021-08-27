import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    //Important Variables
    private static HUD hud;
    private final Handler handler;
    private Thread thread;
    private final Camera camera;
    public final SpriteSheet sprites;
    private boolean running = false;
    private final int width = 1200, height = 600;
    public int FPS;
    private final BufferedImage floor;

    //Other Variables
    public int ammo = 20;
    public int level1EnemyCount = 7;

    public static void main(String[] args) throws InterruptedException {
        new Game();
    }

    //Handles all the major game stuff
    public Game() throws InterruptedException {
        new Window(1200, 600, "Prototype", this); //Creates the game window
        hud = new HUD(this);
        start(); //Starts the thread

        handler = new Handler(); //Declares handler for objects
        camera = new Camera(0, 0); //declares the camera
        this.addKeyListener(new KeyInput(handler)); //Listen for keyboard input
        this.addMouseListener(new MouseInput(handler, camera, this)); //listen for mouse input

        BufferedImageLoader loader = new BufferedImageLoader(); //Declare the image loader
        BufferedImage level = loader.loadImage("/level.png"); //declare image to load as the level
        BufferedImage SpriteSheet = loader.loadImage("/spritesheet.png");

        sprites = new SpriteSheet(SpriteSheet);

        floor = sprites.grabImage(2, 2, 32, 32);
        loadLevel(level);

        if (level1EnemyCount <= 0){
            stop();
            new Game();
        }
    }
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double tickRate = 64.0;
        double ns = 1000000000 / tickRate;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int maxFPS = 120;

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
            if(FPS >= maxFPS) FPS = maxFPS;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
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
                //red == 0 && green == 0 && blue == 255
                if(red == 0 && green == 0 && blue == 255) handler.addObject(new Block(xx*32, yy*32, ID.Block, sprites)); //blue square on image loaded will be a block.
                if(red == 0 && green == 255 && blue == 0) handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, this, hud, sprites));
                if(red == 255 && blue == 0 && green == 0) handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, hud, sprites, this));
                if(red == 255 && green == 0 && blue == 255) handler.addObject(new Ammo(xx*32, yy*32, ID.Crate, sprites)); //Pink is ammo
                if(red == 255 && green == 160 && blue == 0)handler.addObject(new Healthpack(xx*32, yy*32, ID.Healthpack, sprites)); //Orange is Healthpack
                if(red == 255 && green == 255 && blue == 0) handler.addObject(new FastFire(xx*32, xx*32, ID.Speed, sprites)); //Yellow is Speed
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

        gfx2D.translate(-camera.getX(), -camera.getY());
        for(int xx = 0; xx < 30 * 72; xx += 32)
            for(int yy = 0; yy < 30 * 72; yy += 32){
                gfx.drawImage(floor, xx, yy, null);
            }

        handler.render(gfx);
        gfx2D.translate(camera.getX(), camera.getY());

        //healthbar
        hud.render(gfx);

        gfx.setColor(Color.white);
        gfx.drawString("DEVELOPMENT BUILD - V0.4.2", 10, 550);
        //////////////////////////////////
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
