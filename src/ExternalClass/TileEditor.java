package ExternalClass;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class TileEditor implements Runnable {
    private TileEditorFrame tileEditorFrame;
    private int imageWidth, imageHeight, levelWidth, levelHeight;
    
    private BufferStrategy imgbs, lvlbs;
    private Graphics imgg, lvlg;
    
    private Thread thread;
    
    private boolean running = false, update = false;
    
    private ImageSheet imageSheet;
    private LevelSheet levelSheet;
    
    private Assets assets;
    
    private MouseManager imageMouseManager;
    private MouseManager levelMouseManager;
    
    private Handler handler;
    
    private String imagePath, levelPath;
    
    public TileEditor(String imageTitle, int imageWidth, int imageHeight, int levelWidth, int levelHeight) {
        handler = new Handler(this);
        tileEditorFrame = new TileEditorFrame(handler, imageTitle, imageWidth, imageHeight, levelWidth, levelHeight);
        imageMouseManager = new MouseManager();
        levelMouseManager = new MouseManager();
        
        start();
    }
    
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void init() {
        Assets.init(tileEditorFrame.getImageFileName());
        imageSheet = new ImageSheet(handler, tileEditorFrame.getImageFileName());
        levelSheet = new LevelSheet(handler, tileEditorFrame.getLevelFileName(), tileEditorFrame.getImageFileName());
        tileEditorFrame.setImageWidthAndHeight(imageSheet.getImageWidth(), imageSheet.getImageHeight());
        tileEditorFrame.setLevelWidthAndHeight(levelSheet.getLevelWidth() * 16, levelSheet.getLevelHeight() * 16);
        tileEditorFrame.showFrame();
        
        tileEditorFrame.getFrame().addMouseListener(imageMouseManager);
        tileEditorFrame.getFrame().addMouseListener(levelMouseManager);
        tileEditorFrame.getImageCanvas().addMouseListener(imageMouseManager);
        tileEditorFrame.getLevelCanvas().addMouseListener(levelMouseManager);
        tileEditorFrame.getImageCanvas().addMouseMotionListener(imageMouseManager);
        tileEditorFrame.getLevelCanvas().addMouseMotionListener(levelMouseManager);
    }
    
    public void render() {
        imgbs = tileEditorFrame.getImageCanvas().getBufferStrategy();
        
        if (imgbs == null) {
            tileEditorFrame.getImageCanvas().createBufferStrategy(3);
            return;
        }
        imgg = imgbs.getDrawGraphics();
        imgg.clearRect(0, 0, imageWidth, imageHeight);
        imageSheet.render(imgg);
        imgbs.show();
        imgg.dispose();
        
        
        lvlbs = tileEditorFrame.getLevelCanvas().getBufferStrategy();
        if (lvlbs == null) {
            tileEditorFrame.getLevelCanvas().createBufferStrategy(3);
            return;
        }
        lvlg = lvlbs.getDrawGraphics();
        lvlg.clearRect(0, 0, levelWidth, levelHeight);
        levelSheet.render(lvlg);
        lvlbs.show();
        lvlg.dispose();
    }
    
    public void update() {
        imageSheet.update();
        levelSheet.update();
    }

    @Override
    public void run() {
        init();
        while (running) {
            update();
            render();
        }
        stop();
    }

    public MouseManager getImageMouseManager() {
        return imageMouseManager;
    }
    
    public MouseManager getLevelMouseManager() {
        return levelMouseManager;
    }
    
    public Canvas getImageCanvas() {
        return tileEditorFrame.getImageCanvas();
    }
    
    public Canvas getLevelCanvas() {
        return tileEditorFrame.getLevelCanvas();
    }
    
    public ImageSheet getImageSheet() {
        return imageSheet;
    }
    
    public LevelSheet getLevelSheet() {
        return levelSheet;
    }
}
