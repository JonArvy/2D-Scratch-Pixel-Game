package Classes;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    public String title;
    private int width, height;
    private Display display;
    private int x = 0, y = 0, pos = 2;
    boolean moving_up = false;
    private BufferStrategy bs;
    private Graphics g;
    
    private Thread thread;
    private boolean running = false;
    
    private State gameState;
    private State homeState;
    private State settingState;
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private GameCamera gameCamera;
    
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    public void init() {
        display = new Display(title, width, height);
        display.getJFrame().addKeyListener(keyManager);
        display.getJFrame().addMouseListener(mouseManager);
        display.getJFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        Assets.init();
        Tile.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        
        gameState = new GameState(handler);
        homeState = new HomeState(handler);
        settingState = new SettingState(handler);
        
        State.setState(gameState);
        
    }
    
    public void tick() {
        keyManager.tick();
        
        if (State.getState() != null)
            State.getState().tick();
    }
    
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        
        if (State.getState() != null)
            State.getState().render(g);
        
        bs.show();
        g.dispose();
    }
    
    public void run() {
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += (now - lastTime);
            lastTime = now;
            
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                //System.out.println(ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public GameCamera getGameCamera() {
        return gameCamera;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}