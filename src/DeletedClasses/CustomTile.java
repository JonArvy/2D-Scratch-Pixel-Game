package DeletedClasses;

import Classes.Assets;
import Classes.Tile;


public class CustomTile extends Tile{
    
    public CustomTile(int id) {
        super(Assets.tiles[id], id);
    }
}
