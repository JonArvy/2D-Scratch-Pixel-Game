package Classes;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature {
    
    private Animation ani;
    
    private int dir;
    public Player(Handler handler, float x, float y, int dir) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 4;
        bounds.y = 4;
        bounds.width = 24;
        bounds.height = 26;
        
        this.dir = dir;
        
        ani = new Animation(100, Assets.plbody);
    }

    @Override
    public void tick() {
        getInput();
        move();
        if (xMove != 0.0 || yMove != 0.0) {
            ani.tick(true);
        } else {
            ani.tick(false);
        }
        handler.getGameCamera().centerOnEntity(this);
    }
    
    private void getInput() {
        xMove = 0;
        yMove = 0;
        
        if (handler.getKeyManager().up) {
            yMove = -speed;
            dir = 0;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            dir = 2;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            dir = 1;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            dir = 3;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(ani.getCurrentFrame(dir), (int) (x - handler.getGameCamera().getxOffset()), (int) (y + 2 - handler.getGameCamera().getyOffset()), width, height, null);
        g.drawImage(Assets.plhead[dir], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - 12 - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
}
