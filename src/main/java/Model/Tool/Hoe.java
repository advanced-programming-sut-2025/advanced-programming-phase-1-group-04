package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Map.TileType;
import Model.Result;

public class Hoe implements Tool{
    private ToolLevel level;

    public Hoe (ToolLevel level) {
        this.level = level;
    }

    @Override
    public Result upgrade() {
        String pre = "previous level: ";
        String cur = "\ncurrent level: ";
        if (level == ToolLevel.Starter) {
            level = ToolLevel.Copper;
            pre = pre + "Starter";
            cur = cur + "Copper";
            return new Result(true, "hoe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Copper) {
            level = ToolLevel.Steel;
            pre = pre + "Copper";
            cur = cur + "Steel";
            return new Result(true, "hoe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Steel) {
            level = ToolLevel.Gold;
            pre = pre + "Steel";
            cur = cur + "Gold";
            return new Result(true, "hoe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Gold) {
            level = ToolLevel.Iridium;
            pre = pre + "Gold";
            cur = cur + "Iridium";
            return new Result(true, "hoe upgraded successfully.\n" + pre + cur);
        }
        return new Result(false, "hoe is already upgraded!\ncurrent level: Iridium");
    }

    @Override
    public Result use(Tile tile) {
        App.getCurrentGame().getCurrentPlayer().addEnergy(-1 * getEnergyConsumption(true));
        if (tile.getType() != TileType.Ground) {
            return new Result(false, "the selected tile is not a ground tile!");
        }
        if (tile.getItem() != null) {
            return new Result (false, "the selected tile is not empty!");
        }
        if (tile.isPlowed()) {
            return new Result (false, "the selected tile is already plowed!");
        }
        tile.setPlowed(true);
        return new Result(true, "the selected tile is now plowed and ready to get planted.");
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        if (level == ToolLevel.Starter) {
            return 5;
        }
        else if (level == ToolLevel.Copper) {
            return 4;
        }
        else if (level == ToolLevel.Steel) {
            return 3;
        }
        else if (level == ToolLevel.Gold) {
            return 2;
        }
        return 1;
    }

    @Override
    public String getLevelString() {
        return level.name();
    }

    @Override
    public String getName() {
        return "Hoe";
    }

    @Override
    public int getPrice() {
        return 0; //TODO
    }

    public ToolLevel getLevel() {
        return level;
    }
}
