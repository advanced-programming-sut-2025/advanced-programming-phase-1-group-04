package Model.Shop.FishShop;

import Model.App;
import Model.Cooking.Food;
import Model.Cooking.FoodType;
import Model.Crafting.CraftRecipe;
import Model.Plants.Seed;
import Model.Result;
import Model.Shop.Shop;
import Model.Shop.ShopType;
import Model.Tool.FishingPole;
import Model.Tool.FishingPoleType;

import java.util.HashMap;

public class FishShop implements Shop {
    private final ShopType type;
    private HashMap<FishShopStock, Integer> stock = new HashMap<>();

    public FishShop() {
        type = ShopType.FishShop;
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
        String result = "** Fish shop all products: \n\n";
        for (FishShopStock s : stock.keySet()) {
            result = result + "+" + s.getName() + ":\ndescription: " + s.getDescription() +
                    "\nprice: " + s.getPrice() + "\ndaily limit: 1\n\n";
        }
        return result;
    }

    @Override
    public String showAvailableProducts() {
        String result = "** Fish shop available products:\n\n";
        for (FishShopStock s : stock.keySet()) {
            if (stock.get(s) > 0) {
                result = result + "+" + s.getName() + ":\ndescription: " + s.getDescription() +
                        "\nprice: " + s.getPrice() + "\ndaily limit: 1\n\n";
            }
        }
        return result;
    }

    @Override
    public Result buy(String productName, int number, String animalName) {
        if (productName == null) {
            return new Result(false, "invalid product name!");
        }
        if (productName.equalsIgnoreCase("fish smoker") || productName.equalsIgnoreCase("fish smoker recipe") ||
                productName.equalsIgnoreCase("fish smoker (recipe)")) {
            if (stock.get(FishShopStock.FishSmoker) <= 0 || number > 1) {
                return new Result(false, "due to the daily limit of this item, you can't buy it now!");
            }
            if (FishShopStock.FishSmoker.getPrice() * number > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!");
            }
            if (!App.getCurrentGame().getCurrentPlayer().addToCraftRecipes(CraftRecipe.FishSmoker)) {
                return new Result(false, "you already have this recipe!");
            }
            App.getCurrentGame().getCurrentPlayer().addCount(-10000);
            stock.put(FishShopStock.FishSmoker, 0);
            return new Result(true, "fish smoker added to your craft recipes.");
        }
        if (productName.equalsIgnoreCase("trout soup")) {
            if (stock.get(FishShopStock.TroutSoup) <= 0 || number > 1) {
                return new Result(false, "due to the daily limit of this item, you can't buy it now!");
            }
            if (FishShopStock.TroutSoup.getPrice() * number > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!");
            }
            if (!App.getCurrentGame().getCurrentPlayer().addItemToInventory(new Food (FoodType.TroutSoup), 1)) {
                return new Result(false, "you can't add this item to your inventory!");
            }
            return new Result(true, "Trout soup added to your inventory.");
        }
        if (productName.equalsIgnoreCase("bamboo pole") || productName.equalsIgnoreCase("fiberglass pole") ||
                productName.equalsIgnoreCase("iridium pole") || productName.equalsIgnoreCase("bamboo rod") ||
                productName.equalsIgnoreCase("fiberglass rod") || productName.equalsIgnoreCase("iridium rod")) {
            return new Result(false, "you must be equipped with your current fishing pole and then use upgrade command!");
        }
        if (productName.equalsIgnoreCase("training pole") || productName.equalsIgnoreCase("training rod")) {
            if (App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithName("Fishing pole") != null) {
                return new Result(false, "you already have a fishing pole!");
            }
            if (stock.get(FishShopStock.TrainingRod) <= 0 || number > 1) {
                return new Result(false, "due to the daily limit of this item, you can't buy it now!");
            }
            if (FishShopStock.TrainingRod.getPrice() * number > App.getCurrentGame().getCurrentPlayer().getCount()) {
                return new Result(false, "you don't have enough money!");
            }
            App.getCurrentGame().getCurrentPlayer().addItemToInventory(new FishingPole(FishingPoleType.Training), 1);
            return new Result(true, "training rod added successfully.");
        }
        return new Result(false, "invalid product name!");
    }

    @Override
    public void resetStock() {
        for (FishShopStock s : FishShopStock.values()) {
            stock.put(s, 1);
        }
    }
}
