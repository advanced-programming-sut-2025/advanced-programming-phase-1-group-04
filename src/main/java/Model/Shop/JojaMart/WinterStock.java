package Model.Shop.JojaMart;

import Model.Shop.ProductInterface;

public enum WinterStock implements ProductInterface {
    PowdermelonSeeds("This special melon grows in the winter. Takes 7 days to grow." , "Powdermelon Seeds" , 20 , 10);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    WinterStock(String description, String name, int price, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getDailyLimit() {
        return dailyLimit;
    }
}
