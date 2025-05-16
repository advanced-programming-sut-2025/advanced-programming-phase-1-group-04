package Model.Tool;

import Model.App;
import Model.Crafting.Craft;
import Model.Map.BuildingType;
import Model.Map.Stone;
import Model.Map.Tile;
import Model.Plants.ForagingMineral;
import Model.Plants.ForagingMineralType;
import Model.Player.Player;
import Model.Player.Skill;
import Model.Result;
import Model.Time.Weather;

import java.util.ArrayList;
import java.util.Collections;

public class Pickaxe implements Tool {
    ToolType type = ToolType.Pickaxe;
    private ToolLevel level;
    int price;

    public Pickaxe (ToolLevel level) {
        this.level = level;
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
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
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
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
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
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
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
            return new Result(true, "pickaxe upgraded successfully.\n" + pre + cur);
        }
        return new Result(false, "pickaxe is already upgraded!\ncurrent level: Iridium");
    }

    @Override
    public Result use(Tile tile) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (tile.isPlowed()) {
            tile.setItem(null);
            tile.setPlowed(false);
            player.addEnergy(-1 * getEnergyConsumption(true));
            return new Result(true, "the selected tile is no longer plowed.");
        }
        if (tile.getItem() != null && tile.getItem() instanceof Stone) {
            if (player.getAbilityLevel(Skill.Mining) >= 2) {
                if (!player.addItemToInventory(new Stone(), 2)) {
                    player.addEnergy(-1 * getEnergyConsumption(false));
                    return new Result(false, "can't add the stone to inventory!");
                }
                player.addEnergy(-1 * getEnergyConsumption(false));
                player.addAbility(Skill.Mining, 10);
                player.addAbility(Skill.Foraging, 10);
                tile.setItem(null);
                return new Result(true, "you destroyed a stone.\ndue to your mining ability level, 2 stones are added to inventory.");
            }

            if (!player.addItemToInventory(new Stone(), 1)) {
                player.addEnergy(-1 * getEnergyConsumption(false));
                return new Result(false, "can't add the stone to inventory!");
            }
            player.addEnergy(-1 * getEnergyConsumption(false));
            player.addAbility(Skill.Mining, 10);
            player.addAbility(Skill.Foraging, 10);
            tile.setItem(null);
            return new Result(true, "you destroyed a stone.");
        }
        if (tile.getItem() != null && tile.getItem() instanceof ForagingMineral) {
            if (!allowedMinerals().contains(((ForagingMineral) tile.getItem()).getType())) {
                player.addEnergy(-1 * getEnergyConsumption(false));
                return new Result(false, "your current pickaxe is not able to break this mineral!");
            }

            tile.setItem(null);
            player.addEnergy(-1 * getEnergyConsumption(true));
            player.addAbility(Skill.Mining, 10);
            player.addAbility(Skill.Foraging, 10);
            if (player.getAbilityLevel(Skill.Mining) >= 2) {
                player.addItemToInventory(new Stone(), 1);
                return new Result(true, "you destroyed a " + tile.getItem().getName() +
                        "\ndue to your mining skill level, 2 " + tile.getItem().getName() + "s are added to inventory.");
            }
            player.addItemToInventory(new Stone(), 1);
            return new Result(true, "you destroyed a " + tile.getItem().getName());
        }
        if (tile.getItem() != null && tile.getItem() instanceof Craft craft) {
            if (!player.addItemToInventory(craft, 1)) {
                return new Result(false, "your inventory doesn't have enough capacity!");
            }
            player.addEnergy(-1 * getEnergyConsumption(true));
            tile.setItem(null);
            return new Result(true, craft.getName() + " added to inventory.");
        }
        if (tile.getItem() != null) {
            player.addEnergy(-1 * getEnergyConsumption(true));
            tile.setItem(null);
            return new Result(true, "the item is no longer on the tile.");
        }
        player.addEnergy(-1 * getEnergyConsumption(false));
        return new Result(false, "nothing can be done on the selected tile!");
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        int base = 0;
        if (useSuccess) {
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Mining)) {
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
                }
                else if (level == ToolLevel.Copper) {
                    base = 4;
                }
                else if (level == ToolLevel.Steel) {
                    base = 3;
                }
                else if (level == ToolLevel.Gold) {
                    base = 2;
                }
                else {
                    base = 1;
                }
            }
        }
        else {
            if (4 == App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Mining)) {
                if (level == ToolLevel.Starter) {
                    base = 3;
                }
                else if (level == ToolLevel.Copper) {
                    base = 2;
                }
                else if (level == ToolLevel.Steel) {
                    base = 1;
                }
                else if (level == ToolLevel.Gold) {
                    base = 0;
                }
                else {
                    base = 0;
                }
            }
            else {
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
        }
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return (int) (base * 1.5);
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return base * 2;
        }
        return base;
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
        return price;
    }

    public ToolLevel getLevel() {
        return level;
    }

    @Override
    public ToolType getType() {
        return type;
    }

    private ArrayList<ForagingMineralType> allowedMinerals () {
        ArrayList<ForagingMineralType> minerals = new ArrayList<>();
        if (level == ToolLevel.Starter) {
            minerals.add(ForagingMineralType.Copper);
            minerals.add(ForagingMineralType.Coal);
        }
        else if (level == ToolLevel.Copper) {
            minerals.add(ForagingMineralType.Copper);
            minerals.add(ForagingMineralType.Coal);
            minerals.add(ForagingMineralType.Iron);
        }
        else if (level == ToolLevel.Steel) {
            for (ForagingMineralType m : ForagingMineralType.values()) {
                if (m != ForagingMineralType.Iriduim) {
                    minerals.add(m);
                }
            }
        }
        else {
            Collections.addAll(minerals, ForagingMineralType.values());
        }
        return minerals;
    }
}
