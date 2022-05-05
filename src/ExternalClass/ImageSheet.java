package ExternalClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageSheet {
    private Handler handler;
    private boolean starting = false, rend = false;
    private int startX = 0, startY = 0, endX = 0, endY = 0, imageWidth, imageHeight;
    private BufferedImage img;
    private TileData tiledata;
    
    public ImageSheet(Handler handler, String imageName) {
        this.handler = handler;
        this.img = Utils.loadImage(imageName);
        this.imageWidth = img.getWidth();
        this.imageHeight = img.getHeight();
        tiledata = new TileData(handler, img);
        rend = false;
    }
    
    public void update() {
        if (!(handler.getImageMouseManager().isLeftClick())) {
            starting = false;
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
            //System.out.println("Notleft");
        }
        if (handler.getImageMouseManager().isLeftClick() && starting == false) {
            starting = true;
            startX = handler.getImageMouseManager().getMouseX() / 16 * 16;
            startY = handler.getImageMouseManager().getMouseY() / 16 * 16;
            endX = handler.getImageMouseManager().getMouseX() / 16 * 16 + 16;
            endY = handler.getImageMouseManager().getMouseY() / 16 * 16 + 16;
            //System.out.println(handler.getImageMouseManager().getMouseX() + " " + handler.getImageMouseManager().getMouseY());
        } else if (handler.getImageMouseManager().isLeftClick() && starting == true) {
            if (endX < 0 || endY < 0 || endX > img.getWidth() || endY > img.getHeight()) {
                if (endX < 0) {
                    endX = 0;
                }
                if (endY < 0) {
                    endY = 0;
                }
                if (endX > img.getWidth()) {
                    endX = img.getWidth();
                }
                if (endY > img.getHeight()) {
                    endY = img.getHeight();
                }
            } else {
                endX = handler.getImageMouseManager().getMouseUnClickX() / 16 * 16 + 16;
                endY = handler.getImageMouseManager().getMouseUnClickY() / 16 * 16 + 16;
                
            }
            rend = false;
            
        }
        
        //System.out.println(startX + " " + startY + " " + endX + " " + endY);
        //System.out.println(startX + " " + startY + " " + endX + " " + endY);
    }
    
    public void render(Graphics g) {
        if (!rend) {
            g.drawImage(img, 0, 0, null);
            tiledata.loadData();
//            for (int i = 0; i < k.length; i++) {
//                for (int o = 0; o < k[i].length; o++) {
//                    System.out.print(k[i][o]);
//                }
//                System.out.println();
//            }
            rend = true;
        }
        Color transparent = new Color(255, 255, 255, 100);
        g.setColor(transparent);
        
        if (!starting) return;
        if (endX < 0 || endY < 0 || endX > img.getWidth() || endY > img.getHeight()
          ||startX < 0 || startY < 0 || startX >= img.getWidth() || startY >= img.getHeight()) return;
        if (0 <= (endX - startX) && 0 <= (endY - startY)) {
            drawRectangleBounds(g, startX, startY, Math.max(16, (endX - startX)), Math.max(16, (endY - startY)));
        } else if (0 > (endX - startX) && 0 > (endY - startY)) {
            drawRectangleBounds(g, endX, endY, Math.max(16, Math.abs(startX - endX) + 16), Math.max(16, Math.abs(startY - endY) + 16));
        } else if (0 <= (endX - startX) && 0 > (endY - startY)) {
            drawRectangleBounds(g, startX, endY, Math.max(16, (endX - startX)), Math.max(16, (startY - endY + 16)));
        } else if (0 > (endX - startX) && 0 <= (endY - startY)) {
            drawRectangleBounds(g, endX, startY, Math.max(16, (startX - endX + 16)), Math.max(16, (endY - startY)));
        }
    }
    
    public void drawRectangleBounds(Graphics g, int x, int y, int ex, int ey) {
        g.fillRect(x, y, ex, ey);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ex - 1, ey - 1);
        tiledata.setCoordinates(x, y, ex, ey);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }
    
    public TileData getTileData() {
        return this.tiledata;
    }
}
