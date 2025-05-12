package Model.Tool;

import Model.Map.Item;
import Model.Map.Tile;

public interface Tool extends Item {
    public void upgrade();
    public boolean use (Tile tile);
    public int getEnergyConsumption(boolean useSuccess);
    public String getLevelString ();
}
