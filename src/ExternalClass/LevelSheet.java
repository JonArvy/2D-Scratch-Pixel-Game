package ExternalClass;

import java.awt.Graphics;
import java.util.ArrayList;

public class LevelSheet {
    private ArrayList<String> levelData, updatedLevelData;
    private String levelName;
    private int levelWidth = 0, levelHeight = 0, levelStart = 0, levelEnd = 0, npcStart = 0, npcEnd = 0;
    private int startX, startY;
    private Handler handler;
    private boolean rend = false;
    
    public LevelSheet(Handler handler, String levelName, String imagename) {
        this.levelName = levelName;
        this.handler = handler;
        update();
    }
    
    public void render(Graphics g) {
        if (rend) {
            updateTileSet();
//            if (endX < 0 || endY < 0 || endX > img.getWidth() || endY > img.getHeight() || 
//                startX < 0 || startY < 0 || startX >= img.getWidth() || startY >= img.getHeight()) return;
//            if (0 <= (endX - startX) && 0 <= (endY - startY)) {
//              drawRectangleBounds(g, startX, startY, Math.max(16, (endX - startX)), Math.max(16, (endY - startY)));
//            } else if (0 > (endX - startX) && 0 > (endY - startY)) {
//              drawRectangleBounds(g, endX, endY, Math.max(16, Math.abs(startX - endX) + 16), Math.max(16, Math.abs(startY - endY) + 16));
//            } else if (0 <= (endX - startX) && 0 > (endY - startY)) {
//              drawRectangleBounds(g, startX, endY, Math.max(16, (endX - startX)), Math.max(16, (startY - endY + 16)));
//            } else if (0 > (endX - startX) && 0 <= (endY - startY)) {
//              drawRectangleBounds(g, endX, startY, Math.max(16, (startX - endX + 16)), Math.max(16, (endY - startY)));
//            }
        } else {
            if ((levelEnd - levelStart - 1) > 0) {
                for (int y = 0; y < levelHeight; y++) {
                    for (int x = 0; x < levelWidth; x++) {
                        String[] toks = levelData.get(levelStart + y + 1).split("\\s+");
                        g.drawImage(Assets.tile[Integer.parseInt(toks[x])], x * 16, y * 16, 16, 16, null);
                    }
                }
                rend = true;
            }
            if (true) {
                int[][] k = handler.getTileData().getData();
                for (int y = 0; y < k.length; y++) {
                    for (int x = 0; x < k[y].length; x++) {
                        if (handler.getLevelMouseManager().isLeftClick()) {
                            String[] toks = levelData.get(levelStart + y + 1).split("\\s+");
//                            g.drawImage(Assets.tile[Integer.parseInt(toks[x])], (handler.getLevelMouseManager().getMouseX() / 16 * 16) + (x * 16),
//                                                                                (handler.getLevelMouseManager().getMouseY() / 16 * 16) + (y * 16),
//                                                                                16, 16, null);
                            g.drawRect((handler.getLevelMouseManager().getMouseX() / 16 * 16) + (x * 16),
                                       (handler.getLevelMouseManager().getMouseY() / 16 * 16) + (y * 16),
                                       16, 16);

                        } else {
                            g.drawImage(Assets.tile[k[y][x]], (handler.getLevelMouseManager().getMouseX() / 16 * 16) + (x * 16),
                                                              (handler.getLevelMouseManager().getMouseY() / 16 * 16) + (y * 16),
                                                              16, 16, null);
                        }
                    }
                }
            }
            rend = true;
        }
    }
    
    public void updateTileSet() {
        //Load Tileset from lvl file and convert it into 2d array
        int[][] lvl = new int[levelHeight][levelWidth];
        for (int i = 0; i < levelData.size(); i++) {
            if (levelStart < i &&  levelEnd > i) {
                for (int x = 0; x < levelWidth; x++) {
                    String[] toks = levelData.get(levelStart + (i - levelStart - 1) + 1).split("\\s+");
                    lvl[i - levelStart - 1][x] = Integer.parseInt(toks[x]);
                }
            }
        }

        //Replace the 2d array data based on tiledata
        for (int y = 0; y < handler.getTileData().getData().length; y++) {
            for (int x = 0; x < handler.getTileData().getData()[y].length; x++) {
                lvl[startY + y][startX + x] = handler.getTileData().getData()[y][x];
            }
        }

        //Update the arraylist
        for (int i = 0; i < levelData.size(); i++) {
            if (levelStart < i &&  levelEnd > i) {
                String templvldata = "";
                for (int x = 0; x < levelWidth; x++) {
                    templvldata += lvl[i - levelStart - 1][x] + " ";
                }
                updatedLevelData.add(templvldata);
            } else {
                updatedLevelData.add(levelData.get(i));
            }
        }
        //Updating tileset
        Utils.saveFile(levelName, updatedLevelData);
    }
    
    public void updateTileData() {
        
    }
    
    public void update() {
        levelData = Utils.loadFile(levelName);
        updatedLevelData = new ArrayList<String>();
        if (handler.getLevelMouseManager().isRightClick()) {
            startX = handler.getLevelMouseManager().getMouseRightClickX() / 16;
            startY = handler.getLevelMouseManager().getMouseRightClickY() / 16;
            rend = true;
        } else if (handler.getLevelMouseManager().isLeftClick()) {
            
        } else {
            rend = false;
        }
        
        for (int i = 0; i < levelData.size(); i++) {
            String[] toks = levelData.get(i).split("\\s+");
            if (toks[0].equals("LEVEL_WIDTH")) {
                levelWidth = Integer.parseInt(toks[1]);
            } else if (toks[0].equals("LEVEL_HEIGHT")) {
                levelHeight= Integer.parseInt(toks[1]);
            } else if (toks[0].equals("LEVELSTART")) {
                levelStart = i;
            } else if (toks[0].equals("LEVELEND")) {
                levelEnd = i;
            } else if (toks[0].equals("NPCSSTART")) {
                npcStart = i;
            } else if (toks[0].equals("NPCSEND")) {
                npcEnd = i;
            } 
            
        }
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public void setLevelWidth(int levelWidth) {
        this.levelWidth = levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

    public void setLevelHeight(int levelHeight) {
        this.levelHeight = levelHeight;
    }
    
    
}
