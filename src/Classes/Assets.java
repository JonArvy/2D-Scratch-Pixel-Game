package Classes;

import java.awt.image.BufferedImage;

public class Assets {
    private static final int TILE_WIDTH = 16, TILE_HEIGHT = 16,
                             HEAD_WIDTH = 32, HEAD_HEIGHT = 32,
                             BODY_WIDTH = 32, BODY_HEIGHT = 32;
    
    public static BufferedImage[] plhead, plbody, tiles;
    
    public static void init() {
        SpriteSheet tile = new SpriteSheet(ImageLoader.loadImage("Small.png"));
        SpriteSheet head = new SpriteSheet(ImageLoader.loadImage("ericka-head1.png"));
        SpriteSheet body = new SpriteSheet(ImageLoader.loadImage("body.png"));
        //For head
        plhead = new BufferedImage[4];
        for (int i= 0; i < 4; i++) {
            plhead[i] = head.crop(0, i * HEAD_HEIGHT, HEAD_WIDTH, HEAD_HEIGHT);
        }
        //For body
        plbody = new BufferedImage[6 * 4];
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                plbody[y * 4 + x] = body.crop(x * HEAD_WIDTH, y * HEAD_HEIGHT, HEAD_WIDTH, HEAD_HEIGHT);
            }
        }
        //For tiles
        tiles = new BufferedImage[50];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 10; x++) {
                tiles[(10 * y) + x] = tile.crop(x * TILE_WIDTH, y * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
            }
        }
    }
}