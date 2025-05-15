package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Map.Coordinate;
import Model.Map.Item;
import Model.Map.Tile;
import Model.Plants.*;
import Model.Result;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Tool.WateringCan;

import java.util.ArrayList;
import java.util.List;

public class PlantController {
    
    public static Result craftInfo (String craftName) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        craftName = craftName.toLowerCase();
        for (CropType t : CropType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (ForagingCropType t : ForagingCropType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (ForagingMineralType t : ForagingMineralType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (FruitType t : FruitType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (SaplingType t : SaplingType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (SeedType t : SeedType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        for (TreeType t : TreeType.values()) {
            if (t == null || t.getName() == null) {
                continue;
            }
            if (t.getName().toLowerCase().equals(craftName)) {
                return new Result(true, t.getInformation());
            }
        }
        return new Result (false, "invalid plant/ fruit name!");
    }

    public static Result plant (String seedName, String direction) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        SeedType seedType;
        SaplingType saplingType = getSaplingTypeByName(seedName);
        if ((seedType = getSeedTypeByName(seedName)) == null && saplingType == null) {
            return new Result(false, "invalid seed/ sapling name!");
        }
        if (seedType != null) {
            return plantInTile(new Seed(seedType, true), GameMenuController.getTileByDirection(direction),
                    App.getCurrentGame().getCurrentTime());
        }
        return plantInTile(new Sapling(saplingType, true), GameMenuController.getTileByDirection(direction),
                App.getCurrentGame().getCurrentTime());
    }

    public static Result showPlant (String X, String Y) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        if (!PlayerController.isParsableInt(X) || !PlayerController.isParsableInt(Y)) {
            return new Result(false, "invalid coordinate!");
        }
        int x = Integer.parseInt(X);
        int y = Integer.parseInt(Y);
        if (x >= 90 || x < 0 || y >= 120 || y < 0) {
            return new Result(false, "invalid coordinate!");
        }
        Item item = App.getCurrentGame().getTile(new Coordinate(x, y)).getItem();
        if (item == null) {
            return new Result(false, "this tile is empty!");
        }
        else if (item instanceof Plant) {
            String result = ((Plant) item).showPlantInfo() + "\nis watered: " +
                    App.getCurrentGame().getTile(new Coordinate(x, y)).isWatered() + "\nfertilize: ";
            if (App.getCurrentGame().getTile(new Coordinate(x, y)).getFertilize() == 0) {
                result = result + "no";
            }
            else if (App.getCurrentGame().getTile(new Coordinate(x, y)).getFertilize() == 0) {
                result = result + "retaining soil";
            }
            else if (App.getCurrentGame().getTile(new Coordinate(x, y)).getFertilize() == 0) {
                result = result + "speed-gro";
            }
            return new Result(true, result);
        }
        return new Result(false, "no plants in this tile!");
    }

    public static Result fertilize (String fertilizer, String direction) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        return new Result(false, "NOT COMPLETED/ TODO"); //TODO
    }

    public static Result howMuchWater () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        Item wateringCan = App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithName("watering can");
        if (wateringCan == null) {
            return new Result(false, "an error occurred!");
        }
        else if (!(wateringCan instanceof WateringCan)) {
            return new Result(false, "an error occurred!");
        }
        int howMuch = ((WateringCan) wateringCan).getWaterAmount();
        return new Result(true, "water amount in your watering can: " + howMuch);
    }



    public static String stagesToString(int[] stages) {
        if (stages == null) {
            return "";
        }
        String result = "" + stages[0];
        int i = 1;
        for (int a : stages) {
            if (i > 0) {
                i = 0;
                continue;
            }
            result = result + "-" + a;
        }
        return result;
    }

    public static String SeasonsToString(List<Season> seasons) {
        if (seasons.isEmpty()) {
            return "";
        }
        String result = seasons.get(0).name();
        int i = 1;
        for (Season s : seasons) {
            if (i > 0) {
                i = 0;
                continue;
            }
            result = result + "-" + s.name();
        }
        return result;
    }

    public static SeedType getSeedTypeByName (String seedName) {
        if (seedName == null) {
            return null;
        }
        seedName = seedName.toLowerCase();
        for (SeedType s : SeedType.values()) {
            if (s.getName().toLowerCase().equals(seedName)) {
                return s;
            }
        }
        return null;
    }

    public static SaplingType getSaplingTypeByName (String saplingName) {
        if (saplingName == null) {
            return null;
        }
        saplingName = saplingName.toLowerCase();
        for (SaplingType s : SaplingType.values()) {
            if (s.getName().toLowerCase().equals(saplingName)) {
                return s;
            }
        }
        return null;
    }

    public static Result plantInTile (Seed seed, Tile tile, DateAndTime time) {
        if (tile == null) {
            return new Result(false, "invalid tile!");
        }
        if (tile.getItem() != null) {
            return new Result(false, "tile isn't empty!");
        }
        if (!tile.isPlowed()) {
            return new Result(false, "this tile has not been plowed");
        }
        Crop crop = new Crop (time, seed.getCrop(), true);
        tile.setItem(crop);
        return new Result(true, seed.getName() + " is planted in selected tile.");
    }

    public static Result plantInTile (Sapling sapling, Tile tile, DateAndTime time) {
        if (tile == null) {
            return new Result(false, "invalid tile!");
        }
        if (tile.getItem() != null) {
            return new Result(false, "tile isn't empty!");
        }
        if (!tile.isPlowed()) {
            return new Result(false, "this tile has not been plowed");
        }
        Tree tree = new Tree(sapling.getTreeType(), time, true);
        tile.setItem(tree);
        return new Result(true, sapling.getName() + " is planted in selected tile.");
    }
}
