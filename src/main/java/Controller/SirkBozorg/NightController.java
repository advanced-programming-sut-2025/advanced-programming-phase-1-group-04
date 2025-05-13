package Controller.SirkBozorg;

import Model.App;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Plants.*;

import java.util.ArrayList;
import java.util.Random;

public class NightController {
    private static Random rand = new Random();

    public static void nightControl() {
        foragingPlants();
    }

    public static void foragingPlants() {
        foragingPlantsForEachFarm(new Coordinate(0, 0), new Coordinate(29, 39));
        if (App.getCurrentGame().getPlayers().size() >= 2) {
            foragingPlantsForEachFarm(new Coordinate(0, 80), new Coordinate(29, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 3) {
            foragingPlantsForEachFarm(new Coordinate(60, 80), new Coordinate(89, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 4) {
            foragingPlantsForEachFarm(new Coordinate(60, 0), new Coordinate(89, 39));
        }
    }

    public static void foragingPlantsForEachFarm(Coordinate c1, Coordinate c2) {
        ArrayList<Forageable> listOfPlants = ForageableFactory.getSeasonPlantForageables(App.getCurrentGame().
                getCurrentTime().getSeason());
        for (int x = c1.getX(); x <= c2.getX(); x++) {
            for (int y = c1.getY(); y < c2.getY(); y++) {
                if (App.getCurrentGame().getTile(new Coordinate(x, y)).getItem() == null &&
                        App.getCurrentGame().getTile(new Coordinate(x, y)).isPlowed()) {
                    if (rand.nextInt(100) == 0) {
                        plantForageable(App.getCurrentGame().getTile(new Coordinate(x, y)),
                                listOfPlants.get(rand.nextInt(listOfPlants.size())));
                    }
                }
            }
        }
    }

    public static void plantForageable (Tile tile, Forageable plantType) {
        if (plantType instanceof ForagingCropType) {
            tile.setItem(new ForagingCrop((ForagingCropType) plantType, false));
        }
        else if (plantType instanceof SeedType) {
            tile.setItem(new Crop(App.getCurrentGame().getCurrentTime(), ((SeedType) plantType).getCrop(),
                    false));
        }
    }
}
