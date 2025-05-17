package Model.Tool;

import Model.Map.Coordinate;
import Model.Map.Item;
import Model.Map.Tile;
import Model.Result;

public interface Tool extends Item {
    public Result upgrade();
    public Result use (Tile tile);
    public Result use (Coordinate direction);
    public int getEnergyConsumption(boolean useSuccess);
    public String getLevelString ();
    public ToolType getType();
}
