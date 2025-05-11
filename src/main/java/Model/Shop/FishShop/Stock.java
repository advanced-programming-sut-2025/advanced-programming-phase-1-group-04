package Model.Shop.FishShop;

import Model.Shop.ProductInterface;

public enum Stock implements ProductInterface {
    FishSmoker("A recipe to make Fish Smoker" , "Fish Smoker (Recipe)" , 10000 , 0 , 1),
    TroutSoup("Pretty salty." , "Trout Soup" , 250 , 0 , 1),
    BambooPole("Use in the water to catch fish." , "Bamboo Pole" , 500 , 0 , 1),
    TrainingRod("It's a lot easier to use than other rods, but can only catch basic fish." , "Training Rod" , 25 , 0 , 1),
    FiberglassRod("Use in the water to catch fish." , "Fiberglass Rod" , 1800 , 2 , 1),
    IridiumRod("Use in the water to catch fish." , "Iridium Rod" , 7500 , 4 , 1),;

    private final String description;
    private final String name;
    private final int price;
    private final int fishingSkill;
    private final int dailyLimit;

    Stock(String description, String name, int price, int fishingSkill, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.fishingSkill = fishingSkill;
        this.dailyLimit = dailyLimit;
    }

    public int getFishingSkill() {
        return fishingSkill;
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
