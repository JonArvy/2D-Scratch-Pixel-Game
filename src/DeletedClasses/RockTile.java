package DeletedClasses;

import Classes.Assets;
import Classes.Tile;

public class RockTile extends Tile {
    
    public RockTile(int id) {
        super(Assets.tiles[11], id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
