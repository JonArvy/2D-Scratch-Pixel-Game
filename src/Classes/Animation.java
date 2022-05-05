package Classes;

import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index, indexx;
    private BufferedImage[] frames;
    private long lastTime, timer;
    private int counter = 0;
    
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        indexx = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void tick(boolean moving) {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (!moving) {
            index = 0;
            indexx = 0;
        } else {
            if (timer > speed) {
                timer = 0;
                index++;
                if (Math.abs((index % 6) - ((index / 6 % 2) * 5)) == 5) {
                    index++;
                } else if (Math.abs((index % 6) - ((index / 6 % 2) * 5)) == 0) {
                    index += 3;
                }
            }
            indexx = Math.abs((index % 6) - ((index / 6 % 2) * 5));
        }
    }
    
    public BufferedImage getCurrentFrame(int dir) {
        return frames[dir + indexx * 4];
    }
}
