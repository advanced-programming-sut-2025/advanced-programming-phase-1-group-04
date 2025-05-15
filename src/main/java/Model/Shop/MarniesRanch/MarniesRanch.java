package Model.Shop.MarniesRanch;

import Model.Animals.Animal;
import Model.App;
import Model.Cooking.Ingredient;
import Model.Cooking.IngredientType;
import Model.Result;
import Model.Shop.Shop;
import Model.Shop.ShopType;
import Model.Tool.MilkPail;
import Model.Tool.Shear;

import java.util.HashMap;

public class MarniesRanch implements Shop {
    private final ShopType type;
    int milkPailNumber = 1;
    int shearNumber = 1;
    private HashMap<MarniesAnimals, Integer> animals = new HashMap<>();

    public MarniesRanch() {
        type = ShopType.MarniesRanch;
        resetStock();
    }

    @Override
    public ShopType getType() {
        return type;
    }

    @Override
    public String getName() {
        return type.getShopName();
    }

    @Override
    public String getOwnerName() {
        return type.getOwnerName();
    }

    @Override
    public int getOpeningTime() {
        return type.getOpeningTime();
    }

    @Override
    public int getClosingTime() {
        return type.getClosingTime();
    }

    @Override
    public String getShopInformation() {
        return "shop name: " + type.getShopName() + "\nowner name: " +type.getOwnerName() +
                "\nopening time: " + type.getOpeningTime() + " AM\nclosing time: " +
                type.getClosingTime() + " PM";
    }

    @Override
    public String showAllProducts() {
        String result = "** Marnie's Ranch shop all products:\n\n* shop inventory:\n\n";
        result = result + "+Hay:\ndescription: Dried grass used as animal food.\nprice: 50\ndaily limit: unlimited\n\n";
        result = result + "+Milk Pail:\ndescription: Gather milk from your animals.\nprice: 1,000g\ndaily limit: 1\n\n";
        result = result + "+Shears:\ndescription: Use this to collect wool from sheep\nprice: 1,000g\ndaily limit: 1\n\n";
        result = result + "* animals:\n\n";
        for (MarniesAnimals a : animals.keySet()) {
            result = result + "+" + a.getName() + ":\ndescription: " + a.getDescription() +
                    "\nprice: " + a.getPrice() + "\ndaily limit: 2\n\n";
        }
        return result;
    }

    @Override
    public String showAvailableProducts() {
        String result = "** Marnie's Ranch shop available products:\n\n* shop inventory:\n\n";
        result = result + "+Hay:\ndescription: Dried grass used as animal food.\nprice: 50\ndaily limit: unlimited\n\n";
        if (milkPailNumber > 0) {
            result = result + "+Milk Pail:\ndescription: Gather milk from your animals.\nprice: 1,000g\ndaily limit: 1\n\n";
        }
        if (shearNumber > 0) {
            result = result + "+Shears:\ndescription: Use this to collect wool from sheep\nprice: 1,000g\ndaily limit: 1\n\n";
        }

        result = result + "* available animals:\n\n";
        for (MarniesAnimals a : animals.keySet()) {
            if (animals.get(a) > 0) {
                result = result + "+" + a.getName() + ":\ndescription: " + a.getDescription() +
                        "\nprice: " + a.getPrice() + "\ndaily limit: 2\n\n";
            }
        }
        return result;
    }

    @Override
    public Result buy(String productName, int number, String animalName) {
        if (productName == null) {
            return new Result(false, "invalid product name!");
        }
        if (productName.equalsIgnoreCase("hay")) {
            if (number * 50 > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!\n" +
                        number * 50 + "g is needed.");
            }
            if (!App.getCurrentGame().getCurrentPlayer().addItemToInventory(new Ingredient(IngredientType.Hay), number)) {
                return new Result(false, "can't add this item(s) to your inventory!");
            }
            return new Result(true, number + " of hay added to your inventory.");
        }

        if (productName.equalsIgnoreCase("milk pail")) {
            if (number > milkPailNumber) {
                return new Result(false, "due to the daily limit of this item, you can't buy it now!");
            }
            if (number * 1000 > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!\n" +
                        number * 1000 + "g is needed.");
            }
            if (App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithName("milk pail") != null) {
                return new Result(false, "you already have a milk pail!");
            }
            if (!App.getCurrentGame().getCurrentPlayer().addItemToInventory(new MilkPail(), number)) {
                return new Result(false, "can't add this item to your inventory!");
            }
            milkPailNumber = 0;
            return new Result(true, "milk pail added to your inventory.");
        }

        if (productName.equalsIgnoreCase("shears") || productName.equalsIgnoreCase("shear")) {
            if (number > shearNumber) {
                return new Result(false, "due to the daily limit of this item, you can't buy it now!");
            }
            if (number * 1000 > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!\n" +
                        number * 1000 + "g is needed.");
            }
            if (App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithName("shear") != null) {
                return new Result(false, "you already have a shear!");
            }
            if (!App.getCurrentGame().getCurrentPlayer().addItemToInventory(new Shear(), number)) {
                return new Result(false, "can't add this item to your inventory!");
            }
            shearNumber = 0;
            return new Result(true, "shears added to your inventory.");
        }

        if (animalName == null) {
            return new Result(false, "invalid animal name!");
        }
        for (MarniesAnimals a : animals.keySet()) {
            if (productName.equalsIgnoreCase(a.getName())) {
                if (1 > animals.get(a)) {
                    return new Result(false, "due to the daily limit of this item, you can't buy it now!");
                }
                if (a.getPrice() > App.getCurrentGame().getCurrentPlayer().getCount()) {
                    return new Result(false, "you don't have enough money!\n" +
                            a.getPrice() + "g is needed.");
                }
                if (App.getCurrentGame().getCurrentPlayer().getFarmBuildingLevel(a.getBuildingType().getType()) < a.getBuildingType().getLevel()) {
                    return new Result(false, "you must upgrade your " + a.getBuildingType().getType().name() +
                            " first!\n" + a.getBuildingType().getName() + " is needed.");
                }
                if (App.getCurrentGame().getCurrentPlayer().getFarmBuildingCapacity(a.getBuildingType().getType()) <= 0) {
                    return new Result(false, "you don't have enough capacity!");
                }
                App.getCurrentGame().getCurrentPlayer().addAnimal(new Animal(a.getType(), animalName));
                return new Result(true, productName + " named " + animalName + " is now added to your animals.");
            }
        }
        return new Result(false, "product name is invalid!");
    }

    @Override
    public void resetStock() {
        milkPailNumber = 1;
        shearNumber = 1;
        for(MarniesAnimals a : MarniesAnimals.values()) {
            animals.put(a, 2);
        }
    }
}
