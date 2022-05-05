package ExternalClass;

import java.awt.Canvas;

public class Handler {
    private TileEditor tileEditor;
    
    public Handler(TileEditor tileEditor) {
        this.tileEditor = tileEditor;
    }

    public TileEditor getTileEditor() {
        return tileEditor;
    }

    public void setTileEditor(TileEditor tileEditor) {
        this.tileEditor = tileEditor;
    }
    
    public MouseManager getImageMouseManager() {
        return tileEditor.getImageMouseManager();
    }
    
    public MouseManager getLevelMouseManager() {
        return tileEditor.getLevelMouseManager();
    }
    
    public Canvas getImageCanvas() {
        return tileEditor.getImageCanvas();
    }
    
    public Canvas getLevelCanvas() {
        return tileEditor.getLevelCanvas();
    }
    
    public TileData getTileData() {
        return tileEditor.getImageSheet().getTileData();
    }
}
