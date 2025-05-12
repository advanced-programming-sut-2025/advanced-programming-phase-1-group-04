package Model.Tool;

import Model.Map.Item;
import Model.Map.Tile;

public interface Tool extends Item {
    public void upgrade();
    public void use (Tile tile);
}
