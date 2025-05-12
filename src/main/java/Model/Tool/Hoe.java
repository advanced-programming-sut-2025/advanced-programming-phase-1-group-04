package Model.Tool;

import Model.Map.Tile;

public class Hoe implements Tool{
    private ToolLevel level;

    public Hoe (ToolLevel level) {
        this.level = level;
    }

    @Override
    public void upgrade() {
        if (level == ToolLevel.Starter) {
            level = ToolLevel.Copper;
        }
        else if (level == ToolLevel.Copper) {
            level = ToolLevel.Steel;
        }
        else if (level == ToolLevel.Steel) {
            level = ToolLevel.Gold;
        }
        else if (level == ToolLevel.Gold) {
            level = ToolLevel.Iridium;
        }
    }

    @Override
    public boolean use(Tile tile) {
        tile.setPlowed(true);
        return true;
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
