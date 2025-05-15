package Controller.SirkBozorg;

import Model.Animals.Animal;
import Model.App;
import Model.Map.BuildingType;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Result;
import Model.Shop.ShopType;
import Model.Time.Weather;

public class AnimalController {
    public static Result buyAnimal (String animal, String name) {
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.MarniesRanch) {
            return new Result(false, "you must be in Marnie's Ranch to be able to buy an animal!");
        } else if (getAnimalByName(name) != null) {
            return new Result(false, "Pet names should be different!");
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
        return new Result(true, "");
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
        if (!tile.getBuildingType().equals(BuildingType.Barn) &&
            !tile.getBuildingType().equals(BuildingType.Coop)) {
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
            App.getCurrentGame().getTile(animal.getCoordinate()).setAnimal(null);
            animal.setCoordinate(null);
            return new Result(true,  name + " successfully entered.");
        }
    }

    public static Result feedAnimal(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        } else if (App.getCurrentGame().getCurrentPlayer().getInventory().removeItem("hay", 1)) {
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
                result.append("Name: ").append(animal.getName()).append("\n");
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
        }

        // TODO: tool use & Aynaz
        return new Result(true, "");
    }

    public static Result sellAnimal(String name) {
        Animal animal = getAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You don't have a pet with that name!");
        }

        App.getCurrentGame().getCurrentPlayer().addCount(animal.getSellPrice());
        App.getCurrentGame().getCurrentPlayer().getMyAnimals().remove(animal);
        return new Result(true, "You successfully sold " + animal.getName() + " for " + animal.getSellPrice());
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
}
