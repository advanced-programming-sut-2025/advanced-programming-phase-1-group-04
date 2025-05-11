package Model.Trade.JojaMart;

import Model.Trade.ProductInterface;

public enum PermanentStock implements ProductInterface {
    JojaCola("The flagship product of Joja corporation." ,"Joja Cola" , 75 , -1),
    AncientSeed("Could these still grow?" , "Ancient Seed" , 500 , 1),
    GrassStarter("Place this on your farm to start a new patch of grass." , "Grass Starter" ,125 , -1),
    Sugar("Adds sweetness to pastries and candies. Too much can be unhealthy." , "Sugar" , 125 , -1),
    WheatFlour("A common cooking ingredient made from crushed wheat seeds." , "Wheat Flour", 125 , -1),
    Rice("A basic grain often served under vegetables." , "Rice", 250 , -1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PermanentStock(String description, String name, int price, int dailyLimit) {
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
