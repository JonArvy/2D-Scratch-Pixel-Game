package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private String path;
    private ArrayList<String> levelData;
    
    private EntityManager entityManager;
    
    public World(Handler handler, String path) {
        this.handler = handler;
        this.path = path;
        levelData = new ArrayList<String>();
        entityManager = new EntityManager(handler, new Player(handler, 100, 100, 2));
        
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    
    public void tick() {
        entityManager.tick();
    }
    
    public void render(Graphics g) {
        if (handler.getKeyManager().refresh) loadWorld(path);
        int xStart = Math.max(0, ((int) handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH));
        int xEnd = Math.min((xStart + (handler.getWidth() / Tile.TILE_WIDTH) + 2), width);
        int yStart = Math.max(0, ((int) handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT));
        int yEnd = Math.min((yStart + (handler.getHeight()/ Tile.TILE_HEIGHT) + 2), height);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        entityManager.render(g);
    }
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.tiles[49];
        Tile t = Tile.tiles[tiles[y][x]];
        if (t == null) return Tile.tiles[0];
        return t;
    }
    
    private void loadWorld(String path) {
        String[][] tokens = getLevelTraits(path);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //tiles[y][x] = Utils.parseInt(tokens[(y * width) + x + 4]);
                tiles[y][x] = Utils.parseInt(tokens[y][x]);
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public String[][] getLevelTraits(String path) {
        levelData = Utils.loadFileAsString(path);
        int levelStart = 0, levelEnd = 0, npcStart = 0, npcEnd = 1;
        for (int i = 0; i < levelData.size(); i++) {
            String tokens[] = levelData.get(i).split("\\s+");
            if (tokens[0].equals("LEVEL_WIDTH")) {
                width = Utils.parseInt(tokens[1]);
            } else if (tokens[0].equals("LEVEL_HEIGHT")) {
                height = Utils.parseInt(tokens[1]);
            } else if (tokens[0].equals("SPAWN_X")) {
                spawnX = Utils.parseInt(tokens[1]);
            } else if (tokens[0].equals("SPAWN_Y")) {
                spawnY = Utils.parseInt(tokens[1]);
            } else if (tokens[0].equals("LEVELSTART")) {
                levelStart = i;
            } else if (tokens[0].equals("LEVELEND")) {
                levelEnd = i;
            } else if (tokens[0].equals("NPCSSTART")) {
                npcStart = i;
            } else if (tokens[0].equals("NPCSEND")) {
                npcEnd = i;
            }
        }
        if ((levelEnd - levelStart - 1) <= 0) {
            width = 0;
            height = 0;
        }
        String finaltoks[][] = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String[] toks = levelData.get(levelStart + 1 + y).split("\\s+");
                finaltoks[y][x] = toks[x];
            }
        }
        entityManager.clearEntity();
        if ((npcEnd - npcStart - 1) > 0) {
            for (int i = 0; i < (npcEnd - npcStart - 1); i++) {
                String[] toks = levelData.get(npcStart + 1 + i).split("\\s+");
                if (toks.length == 4) {
                    addWorldEntity(toks[0], Utils.parseInt(toks[1]) * 16, Utils.parseInt(toks[2]) * 16, Boolean.parseBoolean(toks[3]));
                } else if (toks.length == 8) {
                    addWorldEntity(toks[0], Utils.parseInt(toks[1]) * 16, Utils.parseInt(toks[2]) * 16,
                                            Utils.parseInt(toks[3]) * 16, Utils.parseInt(toks[4]) * 16,
                                            Utils.parseInt(toks[5]), Utils.parseInt(toks[6]),
                                            Boolean.parseBoolean(toks[7]));
                }
            }
        }
        tiles = new int[height][width];
        return finaltoks;
    }
    
    public void addWorldEntity(String img, int x, int y, boolean blocked) {
        BufferedImage entityImage = ImageLoader.loadImage(img);
        entityManager.addEntity(new NPC(handler, entityImage, x, y, blocked));
    }
    
    public void addWorldEntity(String img, int x, int y, int bx, int by, int bw, int bh, boolean blocked) {
        BufferedImage entityImage = ImageLoader.loadImage(img);
        entityManager.addEntity(new NPC(handler, entityImage, x, y, bx, by, bw, bh, blocked));
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
