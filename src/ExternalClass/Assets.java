package ExternalClass;

import java.awt.image.BufferedImage;

public class Assets {
    public static final int TILEWIDTH = 16, TILEHEIGHT = 16;
    public static BufferedImage tilepic;
    public static BufferedImage[] tile;
    
    
    public static void init(String filename) {
        tilepic = Utils.loadImage(filename);
        tile = new BufferedImage[tilepic.getWidth() / 16 * tilepic.getHeight() / 16];
        
        for (int y = 0; y < tilepic.getHeight() / 16; y++) {
            for (int x = 0; x < tilepic.getWidth() / 16; x++) {
                tile[y * (tilepic.getWidth() / 16) + x] = tilepic.getSubimage(x * 16, y * 16, 16, 16);
            }
        }
    }
    
}
