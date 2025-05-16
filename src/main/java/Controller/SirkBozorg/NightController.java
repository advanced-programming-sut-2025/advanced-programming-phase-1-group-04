package Controller.SirkBozorg;

import Model.Animals.Animal;
import Model.Animals.AnimalProduct;
import Model.Animals.AnimalProductType;
import Model.App;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Map.TileType;
import Model.Plants.*;
import Model.Player.Player;
import Model.Shop.Shop;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Time.Weather;

import java.util.*;

public class NightController {
    public static Random rand = new Random();
    private static Coordinate thorCoordinate = null;

    public static void nightControl() {
        // Map:
        waterControl();

        //Time:
        setWeather();
        setTomorrowWeather();
        goToNextDay();

        rainyWeatherEffect();
        randomForagingPlants();
        randomForagingMinerals();
        thorEffect();

        // Player:
        movePlayers();

        //Shops:
        shopStockReset();

        // Animal:
        calculateFriendshipAnimal();
        resetAnimals();
    }

    private static void randomForagingPlants() {
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
        for (int x = c1.getX(); x < c2.getX(); x++) {
            for (int y = c1.getY(); y < c2.getY(); y++) {
                if (App.getCurrentGame().getTile(new Coordinate(x, y)).getItem() == null &&
                        App.getCurrentGame().getTile(new Coordinate(x, y)).getType() == TileType.Ground){
                    if (rand.nextInt(100) < 5) {
                        plantForageable(App.getCurrentGame().getTile(new Coordinate(x, y)),
                                listOfPlants.get(rand.nextInt(listOfPlants.size())));
                    }
                }
            }
        }
    }

    private static void plantForageable (Tile tile, Forageable plantType) {
        if (plantType instanceof ForagingCropType) {
            tile.setItem(new ForagingCrop((ForagingCropType) plantType, false));
        }
        else if (plantType instanceof SeedType) {
            Crop crop;
            if (((SeedType) plantType).getName().equalsIgnoreCase("Mixed Seeds")) {
                Seed randomSeed = new Seed(SeedType.values()[NightController.rand.nextInt(42)]);
                crop = new Crop(randomSeed.getCrop(),false);
            }
            else {
                crop = new Crop (((SeedType) plantType).getCrop(), false);
            }
            tile.setItem(crop);
        }
    }

    private static void setWeather() {
        App.getCurrentGame().getCurrentTime().setWeather(App.getCurrentGame().getTomorrowWeather());
    }

    private static void setTomorrowWeather() {
        Season season = App.getCurrentGame().getCurrentTime().getSeason();
        Weather randomWeather = season.getWeathers().get(rand.nextInt(season.getWeathers().size()));
        App.getCurrentGame().setTomorrowWeather(randomWeather);
    }

    private static void rainyWeatherEffect() {
        if (App.getCurrentGame().getTomorrowWeather().equals(Weather.Rain) || App.getCurrentGame().getTomorrowWeather().equals(Weather.Storm)) {
            Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();
            for (int i = 0; i < 90; i++) {
                for (int j = 0; j < 120; j++) {
                    if (fullMap[i][j].getType().equals(TileType.Ground))
                        fullMap[i][j].setWatered(true);
                }
            }
        }
    }

    private static void thorEffect() {
        if (thorCoordinate != null) {
            Tile tile = App.getCurrentGame().getTile(thorCoordinate);
            if (tile.getType().equals(TileType.Ground)) {
                if (tile.getItem() instanceof Tree tree) {
                    tree.burn();
                } else {
                    tile.setItem(null);
                }
            }
            thorCoordinate = null;
        } else {
            if (App.getCurrentGame().getTomorrowWeather().equals(Weather.Storm)) {
                for (Tile tile: getRandomTilesFromFarm(1, 3)) {
                    if (tile.getItem() instanceof Tree tree) {
                        tree.burn();
                    } else {
                        tile.setItem(null);
                    }
                }
            }
        }
    }

    private static void movePlayers() {
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (MapController.getDestinationEnergy(player.getHouseCoordinate()) <= player.getEnergy() &&
                MapController.getDestinationEnergy(player.getHouseCoordinate()) != -1) {
                player.setCoordinate(player.getHouseCoordinate());
                player.setEnergy(200);
            } else {
                //TODO: Aynaz 75% energy rozane az dast mide
                player.setCoordinate(MapController.getDestination(player.getHouseCoordinate()));
                player.setEnergy(150);
            }
        }
    }

    private static void goToNextDay () {
        DateAndTime t = App.getCurrentGame().getCurrentTime();
        t.setDay(t.getDay() + 1);
        t.setHour(9);
    }

    public static void saveThor(Coordinate Coordinate) {
        thorCoordinate = Coordinate;
    }

    private static List<Tile> getRandomTilesFromFarm(int farm, int count) {
        Set<Tile> selected = new HashSet<>();
        Random rand = new Random();

        int minX = 0;
        int minY = 0;
        int maxX = 30 - 1;
        int maxY = 40 - 1;

        switch (farm) {
            case 2:
                minY = 80;
                maxY = 120 - 1;
                break;
            case 3:
                minX = 60;
                minY = 80;
                maxX = 90 - 1;
                maxY = 120 - 1;
                break;
            case 4:
                minX = 60;
                maxX = 90 - 1;
                break;
        }

        while (selected.size() < count) {
            int x = rand.nextInt(maxX - minX + 1) + minX;
            int y = rand.nextInt(maxY - minY + 1) + minY;
            if (App.getCurrentGame().getTile(new Coordinate(x, y)).getType().equals(TileType.Ground))
                selected.add(App.getCurrentGame().getTile(new Coordinate(x, y)));
        }

        return new ArrayList<>(selected);
    }


    public static void randomForagingMinerals() {
        foragingMineralsForEachFarm(new Coordinate(0, 0), new Coordinate(29, 39));
        if (App.getCurrentGame().getPlayers().size() >= 2) {
            foragingMineralsForEachFarm(new Coordinate(0, 80), new Coordinate(29, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 3) {
            foragingMineralsForEachFarm(new Coordinate(60, 80), new Coordinate(89, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 4) {
            foragingMineralsForEachFarm(new Coordinate(60, 0), new Coordinate(89, 39));
        }
    }

    private static void foragingMineralsForEachFarm(Coordinate c1, Coordinate c2) {
        ArrayList<Forageable> listOfMinerals = ForageableFactory.getMineralForageables();
        for (int x = c1.getX(); x < c2.getX(); x++) {
            for (int y = c1.getY(); y < c2.getY(); y++) {
                if (App.getCurrentGame().getTile(new Coordinate(x, y)).getItem() == null &&
                App.getCurrentGame().getTile(new Coordinate(x, y)).getType() == TileType.Mine){
                    if (rand.nextInt(100) < 5) {
                        App.getCurrentGame().getTile(new Coordinate(x, y)).
                                setItem(new ForagingMineral((ForagingMineralType) listOfMinerals.get(rand.nextInt(listOfMinerals.size()))));

                    }
                }
            }
        }
    }

    private static void calculateFriendshipAnimal() {
        for (Player player: App.getCurrentGame().getPlayers()) {
            for (Animal animal: player.getMyAnimals()) {
                if (!animal.isPetted())
                    animal.addFriendship(-10);
                if (!animal.isFeeded())
                    animal.addFriendship(-20);
                if (animal.getCoordinate() !=  null)
                    animal.addFriendship(-20);
            }
        }
    }

    private static void resetAnimals() {
        for (Player player: App.getCurrentGame().getPlayers()) {
            for (Animal animal: player.getMyAnimals()) {
                setAnimalProduct(animal);
                animal.setFeeded(false);
                animal.setPetted(false);
            }
        }
    }

    private static void setAnimalProduct(Animal animal) {
        //TODO:  یببینننن هر چند روز یه بار میزاد رو هندل نکردم تو داکم نبود هر شب میزاد این
        if (!animal.isFeeded()) {
            animal.setProduct(null);
            return;
        }

        ArrayList<AnimalProductType> productTypes = animal.getType().getProducts();
        AnimalProductType selectedProductType;

        if (productTypes.size() == 1 || animal.getFriendship() < 100) {
            selectedProductType = productTypes.get(0);
        } else {
            double random = 0.5 + Math.random();
            double chance = (animal.getFriendship() + (150 * random)) / 1500.0;

            if (chance >= 1) {
                selectedProductType = productTypes.get(1);
            } else {
                selectedProductType = productTypes.get(0);
            }
        }

        animal.setProduct(new AnimalProduct(selectedProductType, animal.getFriendship()));
    }

    private static void waterControl () {
        waterControlForEachFarm(new Coordinate(0, 0), new Coordinate(29, 39));
        if (App.getCurrentGame().getPlayers().size() >= 2) {
            waterControlForEachFarm(new Coordinate(0, 80), new Coordinate(29, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 3) {
            waterControlForEachFarm(new Coordinate(60, 80), new Coordinate(89, 119));
        }
        if (App.getCurrentGame().getPlayers().size() >= 4) {
            waterControlForEachFarm(new Coordinate(60, 0), new Coordinate(89, 39));
        }
    }

    private static void waterControlForEachFarm (Coordinate c1, Coordinate c2) {
        for (int x = c1.getX(); x < c2.getX(); x++) {
            for (int y = c1.getY(); y < c2.getY(); y++) {
                Tile tile = App.getCurrentGame().getTile(new Coordinate(x, y));
                if (tile.getItem() == null || !(tile.getItem() instanceof Plant)) {
                    tile.setWatered(false);
                    continue;
                }
                if (tile.getFertilize() == 1) {
                    tile.setWatered(false);
                    continue;
                }
                if (tile.getLastTimeWatered() == null) {
                    tile.setItem(null);
                    tile.setWatered(false);
                }
                else if (App.getCurrentGame().getCurrentTime().getDay() - tile.getLastTimeWatered().getDay() >= 2) {
                    tile.setItem(null);
                    tile.setWatered(false);
                }
            }
        }
    }

    private static void shopStockReset () {
        for (Shop shop : App.getCurrentGame().getShops()) {
            shop.resetStock();
        }
    }
}
