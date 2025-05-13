package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Map.TileType;
import Model.Player.Skill;
import Model.Result;

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
    public Result upgrade() {
        String pre = "previous level: ";
        String cur = "\ncurrent level: ";
        if (level == ToolLevel.Starter) {
            level = ToolLevel.Copper;
            pre = pre + "Starter";
            cur = cur + "Copper";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Copper) {
            level = ToolLevel.Steel;
            pre = pre + "Copper";
            cur = cur + "Steel";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Steel) {
            level = ToolLevel.Gold;
            pre = pre + "Steel";
            cur = cur + "Gold";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Gold) {
            level = ToolLevel.Iridium;
            pre = pre + "Gold";
            cur = cur + "Iridium";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        return new Result(false, "watering can is already upgraded!\ncurrent level: Iridium");
    }

    @Override
    public Result use(Tile tile) {
        App.getCurrentGame().getCurrentPlayer().addEnergy(-1 * getEnergyConsumption(true));
        if (waterAmount == 0) {
            return new Result(false, "your watering can is out of water!");
        }
        else if (tile.getType() == TileType.Ground) {
            tile.setWatered(true);
            return new Result(true, "the selected tile is now watered.");
        }
        else if (tile.getType() == TileType.Water) {
            waterAmount = capacity;
            return new Result(true, "your watering can is now full of water.");
        }
        return new Result(false, "invalid tile!");
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
    public String getLevelString() {
        return level.name();
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
