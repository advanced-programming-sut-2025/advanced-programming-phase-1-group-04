package Controller.SirkBozorg;

import Model.Animals.Animal;
import Model.App;
import Model.Map.BuildingType;
import Model.Result;
import Model.Shop.ShopType;

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

        return new Result(true, "");
    }

    public static Result showListAnimals() {

        return new Result(true, "");
    }

    public static Result cheatFriendShipAnimal(String name, String amount) {

        return new Result(true, "");
    }

    public static Result shepherdAnimal(String name, String stringX, String stringY)  {

        return new Result(true, "");
    }

    public static Result feedAnimal(String name) {

        return new Result(true, "");
    }

    public static Result showListProductAnimals() {

        return new Result(true, "");
    }

    public static Result collectAnimalProduce(String name) {

        return new Result(true, "");
    }

    public static Result sellAnimal(String name) {

        return new Result(true, "");
    }


    private static Animal getAnimalByName(String name) {
        for (Animal animal: App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            if (animal.getName().equals(name))
                return animal;
        }

        return null;
    }
}
