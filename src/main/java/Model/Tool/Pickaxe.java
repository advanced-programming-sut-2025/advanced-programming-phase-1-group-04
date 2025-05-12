package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Player.Skill;

public class Pickaxe implements Tool {
    private ToolLevel level;

    public Pickaxe (ToolLevel level) {
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
        //TODO:
        // need getItem in tile class
        //temp:
        return true;
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
