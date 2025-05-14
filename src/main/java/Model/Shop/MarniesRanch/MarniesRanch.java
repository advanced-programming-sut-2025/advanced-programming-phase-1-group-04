package Model.Shop.MarniesRanch;

import Model.Result;
import Model.Shop.Shop;
import Model.Shop.ShopType;

import java.util.HashMap;

public class MarniesRanch implements Shop {
    private final ShopType type;
    int milkPailNumber = 1;
    int shearNumber = 1;
    private HashMap<MarniesAnimals, Integer> animals = new HashMap<>();

    MarniesRanch() {
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
        String result = "** Marnie's Ranch shop all products:\n\n";
        return result;
    }

    @Override
    public String showAvailableProducts() {
        return "";
    }

    @Override
    public Result buy(String productName, int number, String animalName) {
        return null;
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
