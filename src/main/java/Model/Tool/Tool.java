package Model.Tool;

import Model.Map.Item;
import Model.Map.Tile;
import Model.Result;

public interface Tool extends Item {
    public Result upgrade();
    public Result use (Tile tile);
    public int getEnergyConsumption(boolean useSuccess);
    public String getLevelString ();
}
