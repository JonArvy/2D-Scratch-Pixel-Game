package ExternalClass;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class TileData {
    private Handler handler;
    private BufferedImage img;
    private int[][] tilesize, tiledata;
    private int startX, startY, endX, endY;
    
    public TileData(Handler handler, BufferedImage img) {
        this.handler = handler;
        this.img = img;
        tilesize = new int[img.getHeight() / 16][img.getWidth() / 16];
        for (int i = 0; i < tilesize.length; i++) {
            for (int o = 0; o < tilesize[i].length; o++) {
                tilesize[i][o] = (tilesize[i].length * i) + o;
            }
        }
    }
    
    public void setCoordinates(int startX, int startY, int endX, int endY) {
        this.startX = startX / 16;
        this.startY = startY / 16;
        this.endX = endX / 16;
        this.endY = endY / 16;
        
    }
    
    public void loadData() {
        int[][] temptiledata = new int[endY][endX];
        for (int i = 0; i < temptiledata.length; i++) {
            for (int o = 0; o < temptiledata[i].length; o++) {
                temptiledata[i][o] = tilesize[startY + i][startX + o];
            }
        }
        tiledata = temptiledata;
    }
    
    public void setData(int[][] tiledata) {
        this.tiledata = tiledata;
    }
    
    public int[][] getData() {
        return tiledata;
    }
}
