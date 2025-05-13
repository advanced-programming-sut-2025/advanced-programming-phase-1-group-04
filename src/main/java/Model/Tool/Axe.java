package Model.Tool;

import Model.App;
import Model.Map.BuildingType;
import Model.Map.Tile;
import Model.Player.Skill;
import Model.Result;

public class Axe implements Tool{
    private ToolLevel level;
    int price;

    public Axe (ToolLevel level) {
        this.level = level;
        price = 0;
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
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 2000) {
                return new Result(false, "you don't have enough money!\ncost: 2000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-2000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("copper", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 2000;
            level = ToolLevel.Copper;
            pre = pre + "Starter";
            cur = cur + "Copper";
            return new Result(true, "axe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Copper) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("iron", 25)) {
                return new Result(false, "you don't have enough iron ores!\n25 iron ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 5000) {
                return new Result(false, "you don't have enough money!\ncost: 5000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-5000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("iron", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 5000;
            level = ToolLevel.Steel;
            pre = pre + "Copper";
            cur = cur + "Steel";
            return new Result(true, "axe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Steel) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("gold", 25)) {
                return new Result(false, "you don't have enough gold ores!\n25 gold ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 10000) {
                return new Result(false, "you don't have enough money!\ncost: 10000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-10000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("gold", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 10000;
            level = ToolLevel.Gold;
            pre = pre + "Steel";
            cur = cur + "Gold";
            return new Result(true, "axe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Gold) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("iridium", 25)) {
                return new Result(false, "you don't have enough iridium ores!\n25 iridium ores are needed.");
            }
            else if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber("coal", 5)) {
                return new Result(false, "you don't have enough coal!\n5 pieces are needed.");
            }
            else if (App.getCurrentGame().getCurrentPlayer().getCount() < 25000) {
                return new Result(false, "you don't have enough money!\ncost: 25000g.");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-25000);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("iridium", 25);
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory("coal", 5);
            price = 25000;
            level = ToolLevel.Iridium;
            pre = pre + "Gold";
            cur = cur + "Iridium";
            return new Result(true, "axe upgraded successfully.\n" + pre + cur);
        }
        return new Result(false, "axe is already upgraded!\ncurrent level: Iridium");
    }

    @Override
    public Result use(Tile tile) {
        //TODO:
        // need getItem in tile class
        //temp:
        return new Result(false, "TODO");
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        if (useSuccess) {
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Foraging)) {
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
        }
        else {
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Foraging)) {
                if (level == ToolLevel.Starter) {
                    return 3;
                }
                else if (level == ToolLevel.Copper) {
                    return 2;
                }
                else if (level == ToolLevel.Steel) {
                    return 1;
                }
                else if (level == ToolLevel.Gold) {
                    return 0;
                }
                return 0;
            }
            else {
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
        }
    }

    @Override
    public String getLevelString() {
        return level.name();
    }

    @Override
    public String getName() {
        return "Axe";
    }

    @Override
    public int getPrice() {
        return price;
    }

    public ToolLevel getLevel() {
        return level;
    }
}
