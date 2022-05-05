package Classes;

public class TileUnmovable extends Tile {

    public TileUnmovable(int id) {
        super(Assets.tiles[id], id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}
