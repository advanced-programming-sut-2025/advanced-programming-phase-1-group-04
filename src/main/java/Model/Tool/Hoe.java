package Model.Tool;

import Model.Map.Tile;

public class Hoe implements Tool{
    private ToolLevel level;

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
    public void use(Tile tile) {
        tile.setPlowed(true);
    }

    @Override
    public String getName() {
        return "Hoe";
    }

    @Override
    public int getPrice() {
        return 0; //TODO
    }
}
