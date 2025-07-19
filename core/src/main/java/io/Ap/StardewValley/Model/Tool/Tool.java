package io.Ap.StardewValley.Model.Tool;

import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Model.Map.Item;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.Model.Result;

public interface Tool extends Item {
    public Result upgrade();
    public Result use (Tile tile);
    public Result use (Coordinate direction);
    public int getEnergyConsumption(boolean useSuccess);
    public String getLevelString ();
    public ToolType getType();
}
