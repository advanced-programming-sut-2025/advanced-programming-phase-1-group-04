package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Map.TileType;
import Model.Player.Skill;

public class WateringCan implements Tool{
    private int capacity;
    private ToolLevel level;
    private int waterAmount;

    public WateringCan (ToolLevel level) {
        this.level = level;
        if(level == ToolLevel.Starter) {
            capacity = 40;
        }
        else if(level == ToolLevel.Copper) {
            capacity = 55;
        }
        else if(level == ToolLevel.Steel) {
            capacity = 70;
        }
        else if(level == ToolLevel.Gold) {
            capacity = 85;
        }
        else if(level == ToolLevel.Iridium) {
            capacity = 100;
        }
        waterAmount = 0;
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
        if (waterAmount == 0) {
            return false;
        }
        else if (tile.getType() == TileType.Ground) {
            tile.setWatered(true);
            return true;
        }
        else if (tile.getType() == TileType.Water) {
            waterAmount = capacity;
            return true;
        }
        return false;
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Farming)) {
            if (level == ToolLevel.Starter) {
                return 4;
            }
            else if (level == ToolLevel.Copper) {
                return 3;
            }
            else if (level == ToolLevel.Steel) {
                return 2;
            }
            else if (level == ToolLevel.Gold) {
                return 1;
            }
            return 0;
        }
        else {
            if (level == ToolLevel.Starter) {
                return 5;
            } else if (level == ToolLevel.Copper) {
                return 4;
            } else if (level == ToolLevel.Steel) {
                return 3;
            } else if (level == ToolLevel.Gold) {
                return 2;
            }
            return 1;
        }
    }

    @Override
    public String getName() {
        return "Watering can";
    }

    @Override
    public int getPrice() {
        return 0; //TODO
    }

    public int getCapacity() {
        return capacity;
    }

    public ToolLevel getLevel() {
        return level;
    }

    public int getWaterAmount() {
        return waterAmount;
    }
}
