package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    //STATIC STUFF
    
    public static Tile[] tiles = new Tile[256];
    
    public static int[] notSolidTiles = { 0,  1,  8,  9, 10, 18, 19, 28, 29, 30,
                                         31, 38, 39, 40, 41, 42, 43};
    public static int[] solidTiles = { 2,  3,  4,  5,  6,  7, 11, 12, 13, 14,
                                      15, 16, 17, 20, 21, 22, 23, 24, 25, 26,
                                      27, 32, 33, 34, 35, 36, 37, 44, 45, 46,
                                      47, 48, 49};
    
    //CLASS
    public static final int TILE_WIDTH = 16,
                            TILE_HEIGHT = 16;
    
    protected BufferedImage texture;
    protected int id;
    
    public static void init() {
        for (int i = 0; i < solidTiles.length; i++) {
            Tile k = new TileUnmovable(solidTiles[i]);;
            tiles[solidTiles[i]] = k;
        }
        for (int i = 0; i < notSolidTiles.length; i++) {
            Tile k = new TileMovable(notSolidTiles[i]);
            tiles[notSolidTiles[i]] = k;
        }
    }
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public int getId() {
        return id;
    }
}
