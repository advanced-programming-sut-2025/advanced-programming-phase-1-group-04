package Model.Tool;

import Model.App;
import Model.Map.BuildingType;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Map.TileType;
import Model.Player.Skill;
import Model.Result;
import Model.Time.Weather;

public class WateringCan implements Tool{
    ToolType type = ToolType.WateringCan;
    private int capacity;
    private ToolLevel level;
    private int waterAmount;
    int price;

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
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.Blacksmith) {
            return new Result(false, "you are not in black smith building!");
        }
        String pre = "previous level: ";
        String cur = "\ncurrent level: ";
        if (level == ToolLevel.Starter) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("copper", 25)) {
                return new Result(false, "you don't have enough copper ores!\n25 copper ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 1000) {
                return new Result(false, "you don't have enough money!\ncost: 2000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-1000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("copper", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 1000;
            level = ToolLevel.Copper;
            pre = pre + "Starter";
            cur = cur + "Copper";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Copper) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("iron", 25)) {
                return new Result(false, "you don't have enough iron ores!\n25 iron ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 2500) {
                return new Result(false, "you don't have enough money!\ncost: 5000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-2500);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("iron", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 2500;
            level = ToolLevel.Steel;
            pre = pre + "Copper";
            cur = cur + "Steel";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Steel) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("gold", 25)) {
                return new Result(false, "you don't have enough gold ores!\n25 gold ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 5000) {
                return new Result(false, "you don't have enough money!\ncost: 10000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-5000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("gold", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 5000;
            level = ToolLevel.Gold;
            pre = pre + "Steel";
            cur = cur + "Gold";
            return new Result(true, "watering can upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Gold) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("iridium", 25)) {
                return new Result(false, "you don't have enough iridium ores!\n25 iridium ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 12500) {
                return new Result(false, "you don't have enough money!\ncost: 25000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-12500);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("iridium", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 12500;
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
        if (tile == null) {
            return new Result(false, "invalid direction!");
        }
        if (tile.getType() == TileType.Water || tile.getBuildingType() == BuildingType.Well) {
            waterAmount = capacity;
            return new Result(true, "your watering can is now full of water.");
        }

        else if (tile.getType() == TileType.Ground) {
            if (waterAmount <= 0) {
                return new Result(false, "your watering can is out of water!");
            }
            tile.setWatered(true);
            waterAmount--;
            return new Result(true, "the selected tile is now watered.");
        }

        return new Result(false, "invalid tile!");
    }

    @Override
    public Result use(Coordinate c) {
        if (c == null) {
            return new Result(false, "invalid coordinate!");
        }
        Tile t = App.getCurrentGame().getTile(c);
        return use(t);
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        int base = 0;
        if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Farming)) {
            if (level == ToolLevel.Starter) {
                base = 4;
            }
            else if (level == ToolLevel.Copper) {
                base = 3;
            }
            else if (level == ToolLevel.Steel) {
                base = 2;
            }
            else if (level == ToolLevel.Gold) {
                base = 1;
            }
            else {
                base = 0;
            }
        }
        else {
            if (level == ToolLevel.Starter) {
                base = 5;
            } else if (level == ToolLevel.Copper) {
                base = 4;
            } else if (level == ToolLevel.Steel) {
                base = 3;
            } else if (level == ToolLevel.Gold) {
                base = 2;
            }
            base = 1;
        }
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return (int) (base * 1.5);
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return base * 2;
        }

        if (App.getCurrentGame().getCurrentPlayer().isBuffed(Skill.Farming)) {
            base = Math.max(base - 1, 0);
        }
        return base;
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
        return price;
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

    @Override
    public ToolType getType() {
        return type;
    }

}
