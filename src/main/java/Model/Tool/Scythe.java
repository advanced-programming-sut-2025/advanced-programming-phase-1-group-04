package Model.Tool;

import Controller.SirkBozorg.NightController;
import Model.App;
import Model.Cooking.Ingredient;
import Model.Cooking.IngredientType;
import Model.Map.Tile;
import Model.Plants.*;
import Model.Player.Player;
import Model.Player.Skill;
import Model.Result;
import Model.Time.DateAndTime;
import Model.Time.Weather;

public class Scythe implements Tool {
    ToolType type = ToolType.Scythe;

    @Override
    public Result upgrade() {
        return new Result(false, "scythe can't get upgraded");
    }

    @Override
    public Result use(Tile tile) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.addEnergy(-1 * getEnergyConsumption(true));
        if (tile.getItem() == null) {
            return new Result(false, "the selected tile is empty!");
        }
        if (tile.getItem() instanceof Tree tree) {
            if (tree.isBurned()) {
                if (!player.addItemToInventory(new ForagingMineral(ForagingMineralType.Coal), 1)) {
                    return new Result(false, "this tree has been burned. but your inventory doesn't have enough capacity!");
                }
                player.addAbility(Skill.Foraging, 10);
                player.addAbility(Skill.Farming, 5);
                tile.setPlowed(false);
                return new Result(true, "coal added to inventory.");
            }
            else if (tree.hasFruit()) {
                if (!player.addItemToInventory(new Fruit(tree.getFruitType(), qualityConst()), 1)) {
                    return new Result(false, "you can't add this fruit to your inventory!");
                }
                if (!tree.isPurposelyPlanted()) {
                    player.addAbility(Skill.Foraging, 10);
                }
                player.addAbility(Skill.Farming, 5);
                tile.setPlowed(false);
                return new Result(true, tree.getFruitType().getName() + " added to inventory.");
            }
            return new Result(false, "this tree is not ready to get harvested yet!");
        }
        if (tile.getItem() instanceof Crop crop) {
            if (crop.getType() == CropType.Grass) {
                tile.setItem(null);
                if (!player.addItemToInventory(new Ingredient(IngredientType.Fiber), 1)) {
                    return new Result(false, "can't add item to inventory!");
                }
                player.addAbility(Skill.Foraging, 10);
                player.addAbility(Skill.Farming, 5);
                tile.setPlowed(false);
                return new Result(true, "fiber added to inventory.");

            }
            if (crop.isHarvestable()) {
                crop.setQualityConst(qualityConst());
                if (!player.addItemToInventory(cropClone(crop), 1)) {
                    return new Result(false, "can't add item to inventory!");
                }
                if (crop.isOneTime()) {
                    tile.setItem(null);
                }
                else {
                    crop.setLastTimeHarvested(App.getCurrentGame().getCurrentTime());
                }
                if (!crop.isPurposelyPlanted()) {
                    player.addAbility(Skill.Foraging, 10);
                }
                player.addAbility(Skill.Farming, 5);
                tile.setPlowed(false);
                return new Result(true, crop.getName() + " added to inventory.");
            }
            return new Result(false, "this crop is not ready to get harvested yet!");
        }

        if (tile.getItem() instanceof ForagingCrop crop) {
            crop.setQualityConst(qualityConst());
            if (!player.addItemToInventory(cropClone(crop), 1)) {
                return new Result(false, "can't add item to inventory!");
            }

            tile.setItem(null);

            if (!crop.isPurposelyPlanted()) {
                player.addAbility(Skill.Foraging, 10);
            }
            player.addAbility(Skill.Farming, 5);
            tile.setPlowed(false);
            return new Result(true, crop.getName() + " added to inventory.");
        }

        return new Result(false, "TODO"); //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return 3;
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return 4;
        }
        return 2;
    }

    @Override
    public String getLevelString() {
        return "Scythe";
    }

    @Override
    public String getName() {
        return "Scythe";
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public ToolType getType() {
        return type;
    }

    private double qualityConst () {
        double c = 1;
        int a = App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Farming) +
                App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Foraging);
        if (NightController.rand.nextInt(10) < a) {
            c = 1.25;
        }
        if (NightController.rand.nextInt(10) < a - 1) {
            c = 1.5;
        }
        if (NightController.rand.nextInt(10) < a - 3) {
            c = 2;
        }
        return c;
    }

    public Crop cropClone (Crop crop) {
        Crop newCrop = new Crop(crop.getType(), crop.isPurposelyPlanted());
        newCrop.setQualityConst(crop.getQualityConst());
        newCrop.setPlantingDate(crop.getPlantingDate());
        newCrop.setLastTimeHarvested(crop.getLastTimeHarvested());
        newCrop.setTotalHarvestTime(crop.getHarvestTime());
        return newCrop;
    }

    public ForagingCrop cropClone (ForagingCrop crop) {
        ForagingCrop newCrop = new ForagingCrop(crop.getType());
        newCrop.setQualityConst(crop.getQualityConst());
        return newCrop;
    }
}
