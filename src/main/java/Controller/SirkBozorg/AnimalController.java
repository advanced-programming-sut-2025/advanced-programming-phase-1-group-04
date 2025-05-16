package Controller.SirkBozorg;

import Model.Animals.Animal;
import Model.Animals.Fish;
import Model.Animals.FishType;
import Model.App;
import Model.Map.*;
import Model.Player.Player;
import Model.Player.Skill;
import Model.Result;
import Model.Shop.ShopType;
import Model.Time.Weather;
import Model.Tool.FishingPole;
import Model.Tool.FishingPoleType;
import Model.Tool.Tool;
import Model.Tool.ToolType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnimalController {
    public static Result buyAnimal (String animal, String name) {
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.MarniesRanch) {
            return new Result(false, "you must be in Marnie's Ranch to be able to buy an animal!");
        } else if (getAnimalByName(name) != null) {
            return new Result(false, "Pet names should be unique!");
        }

        return App.getCurrentGame().getShop(ShopType.MarniesRanch).buy(animal, 1, name);
    }

    public static Result pet(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        } else if (!isAnimalBesideMe(name)) {
            return new Result(false, "To pet an animal, you need to be within 8 tiles of it!");
        }

        animal.addFriendship(15);
        animal.setPetted(true);
        return new Result(true, "Aghoda che sahebe mehraboni *_*");
    }

    public static Result showListAnimals() {
        StringBuilder result = new StringBuilder();
        result.append("My Animals List:\n").append("_______________________________________\n");
        for (Animal animal: App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            result.append(animal.toString());
        }
        return new Result(true, result.toString());
    }

    public static Result cheatFriendShipAnimal(String name, String stringAmount) {
        int amount = Integer.parseInt(stringAmount);
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name.");
        } else if (amount > 1000 || amount < 0) {
            return new Result(false, "Invalid amount! It should be between: (0-1000)");
        }

        animal.setFriendship(amount);
        return new Result(true, "Animal friendship successfully changed to " + amount);
    }

    public static Result shepherdAnimal(String name, String stringX, String stringY)  {
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        Coordinate coordinate = new Coordinate(x, y);
        Tile tile = App.getCurrentGame().getTile(coordinate);
        Animal animal = getAnimalByName(name);

        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        } else if ((x < 0 || x >= 90) || (y < 0 || y >= 120)) {
            return new Result(false, "Mashti x,y bein (0,0) - (89, 119)");
        } else if (!tile.isWalkable()) {
            return new Result(false, "It's a unwalkable tile!");
        }

        // Go out:
        BuildingType type = tile.getBuildingType();
        if (type == null || (!type.equals(BuildingType.Barn) && !type.equals(BuildingType.Coop))) {
            if (!App.getCurrentGame().getCurrentTime().getWeather().equals(Weather.Sunny)) {
                return new Result(false, "Animals can only go out in sunny weather!");
            }

            tile.setAnimal(animal);
            animal.setCoordinate(coordinate);
            animal.addFriendship(8);
            animal.setFeeded(true);
            return new Result(true, name + " successfully went outside.");
        }
        // Go inside:
        else {
            if (animal.getCoordinate() != null) {
                App.getCurrentGame().getTile(animal.getCoordinate()).setAnimal(null);
                animal.setCoordinate(null);
                return new Result(true,  name + " successfully entered.");
            } else {
                return new Result(true,  "همین الانشم توعه بس!");
            }
        }
    }

    public static Result feedAnimal(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        } else if (!App.getCurrentGame().getCurrentPlayer().getInventory().removeItem("hay", 1)) {
            return new Result(false, "You don't have hay!");
        }

        animal.addFriendship(10);
        animal.setFeeded(true);

        // inventory check with removeItem
        return new Result(true,  name + " was successfully fed.");
    }

    public static Result showListProductAnimals() {
        StringBuilder result = new StringBuilder();
        result.append("My Animal's products List:\n").append("_______________________________________\n");
        for (Animal animal: App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            if (animal.getProduct() != null) {
                result.append("Animal mame: ").append(animal.getName()).append("\n").append("Animal type: "). append(animal.getType()).append("\n");
                result.append(animal.getProductString());
            }
        }
        return new Result(true, result.toString());
    }

    public static Result collectAnimalProduce(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        } else if (animal.getProduct() == null) {
            return new Result(false, name + " doesn't have any product to harvest!"); // TODO: harvest?
        } else if (toolHandleCollect(animal) != null) {
            return toolHandleCollect(animal);
        }

        if (!App.getCurrentGame().getCurrentPlayer().getInventory().addItem(animal.getProduct())) {
            return new Result(true, "Inventory is full. You picked up the product, ama be che gheimati?");
        }

        animal.setProduct(null);
        return new Result(true, "You successfully collect " + animal.getName() + "'s produce.");
    }

    public static Result sellAnimal(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        }

        App.getCurrentGame().getCurrentPlayer().addCount(animal.getSellPrice());
        App.getCurrentGame().getCurrentPlayer().getMyAnimals().remove(animal);
        if (animal.getCoordinate() != null) {
            App.getCurrentGame().getTile(animal.getCoordinate()).setAnimal(null);
        }
        return new Result(true, "You successfully sold " + animal.getName() + " for " + animal.getSellPrice());
    }

    public static Result fishing(String fishingPole) {
        Tool currentTool = App.getCurrentGame().getCurrentPlayer().getCurrentTool();
        FishingPoleType type = getFishingPoleTypeByName(fishingPole);

        // Tool error:
        if (currentTool == null || !currentTool.getType().equals(ToolType.FishingPole)) {
            return new Result(false, "Your current tool isn't fishing pole!");
        } else if (type == null) {
            StringBuilder valid = new StringBuilder();
            for (FishingPoleType f: FishingPoleType.values()) {
                valid.append(f.getName()).append(" ");
            }
            return new Result(false, "Invalid fishing pole name! Valid names: {" + valid + "}");
        } else if (currentTool instanceof FishingPole playerPole) {
            if (!playerPole.getFishingPoleType().equals(type)) {
                return new Result(false, "Your fishing pole type isn't the same with your current tool type!");
            }
        }
        // location error:
        if (!isWaterBesideMe()) {
            return new Result(false, "You have to be near the water to fishing!");
        }

        StringBuilder result = new StringBuilder();
        List<Fish> fishes = getFishes(type);
        Player player = App.getCurrentGame().getCurrentPlayer();

        result.append("You caught ").append(fishes.size()).append(" fish").append(fishes.size() > 1 ? "es" : "").append(":\n");

        for (Fish fish : fishes) {
            result.append("- ").append(fish.getName())
                    .append(" quality: ").append(String.format("(%.2f)", fish.getQuality())).append(fish.getQualityString()).append("\n");
            if (!player.getInventory().addItem(fish)) {
                return new Result(false, "Inventory is full!");
            }
        }

        return new Result(true, result.toString());
    }

    private static List<Fish> getFishes(FishingPoleType poleType) {
        // number of fishes:
        Random random = new Random();
        double M = switch (App.getCurrentGame().getCurrentTime().getWeather()) { //TODO: add to weather field
            case Sunny -> 1.5;
            case Rain -> 1.2;
            case Storm -> 0.5;
            default -> 1.0;
        };

        double R = random.nextDouble();
        int fishCount = (int) Math.ceil(R * M * (App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Fishing) + 2));
        fishCount = Math.min(6, fishCount);

        // type of fishes:
        List<FishType> seasonalFishesTypes = new ArrayList<>();

        for (FishType fish : FishType.values()) {
            if (fish.getSeason().equals(App.getCurrentGame().getCurrentTime().getSeason())) {
                if (!fish.isLegendary())
                    seasonalFishesTypes.add(fish);
                else {
                    if (App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Fishing) == 4) {
                        seasonalFishesTypes.add(fish);
                    }
                }
            }
        }
        Collections.shuffle(seasonalFishesTypes);

        // quality:
        R = random.nextDouble();
        double quality = R * (App.getCurrentGame().getCurrentPlayer().getAbilityLevel(Skill.Fishing) + 2) * poleType.getFishingFactor() / (7 - M);

        List<Fish> result = new ArrayList<>();
        int count = Math.min(fishCount, seasonalFishesTypes.size());
        for (int i = 0; i < count; i++) {
            result.add(new Fish(seasonalFishesTypes.get(i), quality));
        }

        return result;
    }

    private static FishingPoleType getFishingPoleTypeByName(String name) {
        for (FishingPoleType f: FishingPoleType.values()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

    private static Animal getAnimalByName(String name) {
        for (Animal animal: App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            if (animal.getName().equals(name))
                return animal;
        }

        return null;
    }

    private static boolean isAnimalBesideMe(String name) {
        Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();

        int x = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX();
        int y = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY();

        int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if ((newX < 0 || newX >= 90) || (newY < 0 || newY >= 120)) continue;

            Tile tile = fullMap[newX][newY];
            if (tile != null && tile.getAnimal() != null) {
                if (tile.getAnimal().getName().equals(name))
                    return true;
            }
        }

        return false;
    }

    private static boolean isWaterBesideMe () {
        Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();

        int x = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX();
        int y = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY();

        int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if ((newX < 0 || newX >= 90) || (newY < 0 || newY >= 120)) continue;

            Tile tile = fullMap[newX][newY];
            if (tile.getType().equals(TileType.Water)) {
                return true;
            }
        }

        return false;
    }

    private static Result toolHandleCollect(Animal animal) {
        switch (animal.getType()) {
            case Cow, Goat:
                if (App.getCurrentGame().getCurrentPlayer().getCurrentTool().getType().equals(ToolType.MilkPail)) {
                    return null;
                } else {
                    return new Result(false, "For collect goat/cow product, you need milk pail tool!");
                }
            case Sheep:
                if (App.getCurrentGame().getCurrentPlayer().getCurrentTool().getType().equals(ToolType.Shear)) {
                    return null;
                } else {
                    return new Result(false, "For collect sheep product, you need shear tool!");
                }
            case Pig:
                if (animal.getCoordinate() != null) {
                    return null;
                } else {
                    return new Result(false, "For collect pig product, it should be outside!");
                }
            default:
                return null;
        }
    }
}
