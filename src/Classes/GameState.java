package Classes;

import java.awt.Graphics;

public class GameState extends State {
    private World world;
    
    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "Levels/world1.txt");
        handler.setWorld(world);
    }
    
    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
//    public void setWorld() {
//        world = new World(handler, "Levels/world3.txt");
//        handler.setWorld(world);
//    }
}
