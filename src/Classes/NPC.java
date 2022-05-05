package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NPC extends StaticEntity {
    
    private BufferedImage img;
    private boolean blocked;
    
    public NPC(Handler handler, BufferedImage img, float x, float y, boolean blocked) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        
        this.img = img;
        this.blocked = blocked;
        
        if (blocked) {
            bounds.x = 0;
            bounds.y = 0;
            bounds.width = img.getWidth();
            bounds.height = img.getHeight();
        } else {
            bounds.x = 0;
            bounds.y = 0;
            bounds.width = 0;
            bounds.height = 0;
        }
    }
    
    public NPC(Handler handler, BufferedImage img, float x, float y, int boundx, int boundy, int boundw, int boundh, boolean blocked) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        
        this.img = img;
        this.blocked = blocked;
        
        if (!blocked) return;
        bounds.x = boundx;
        bounds.y = boundy;
        bounds.width = boundw;
        bounds.height = boundh;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), img.getWidth(), img.getHeight(), null);
    }
}
