package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Player.Skill;
import Model.Result;

public class Pickaxe implements Tool {
    private ToolLevel level;

    public Pickaxe (ToolLevel level) {
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
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Copper) {
            level = ToolLevel.Steel;
            pre = pre + "Copper";
            cur = cur + "Steel";
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Steel) {
            level = ToolLevel.Gold;
            pre = pre + "Steel";
            cur = cur + "Gold";
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
        }
        else if (level == ToolLevel.Gold) {
            level = ToolLevel.Iridium;
            pre = pre + "Gold";
            cur = cur + "Iridium";
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
        }
        return new Result(false, "pickaxe is already upgraded!\ncurrent level: Iridium");
    }

    @Override
    public Result use(Tile tile) {
        //TODO: معدن و سنگ میخوایم
        if (tile.isPlowed()) {
            tile.setPlowed(false);
            App.getCurrentGame().getCurrentPlayer().addEnergy(-1 * getEnergyConsumption(true));
            return new Result(true, "the selected tile is no longer plowed.");
        }

        App.getCurrentGame().getCurrentPlayer().addEnergy(-1 * getEnergyConsumption(false));
        return new Result(false, "TODO / nothing can be done on the selected tile!");
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        if (useSuccess) {
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Mining)) {
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
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Mining)) {
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
        return "Pickaxe";
    }

    @Override
    public int getPrice() {
        return 0;//TODO
    }

    public ToolLevel getLevel() {
        return level;
    }
}
