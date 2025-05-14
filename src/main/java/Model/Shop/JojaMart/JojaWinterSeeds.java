package Model.Shop.JojaMart;

import Model.Plants.SeedType;
import Model.Shop.ProductInterface;

public enum JojaWinterSeeds implements ProductInterface {
    AncientSeed(SeedType.AncientSeeds, "Could these still grow?" , "Ancient Seed" , 500 , 1),
    GrassStarter(SeedType.GrassStarter, "Place this on your farm to start a new patch of grass." , "Grass Starter" ,125 , Integer.MAX_VALUE),

    PowdermelonSeeds(SeedType.PowdermelonSeeds, "This special melon grows in the winter. Takes 7 days to grow." , "Powdermelon Seeds" , 20 , 10);

    private final SeedType seedType;
    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    JojaWinterSeeds(SeedType seedType, String description, String name, int price, int dailyLimit) {
        this.seedType = seedType;
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

    public SeedType getSeedType() {
        return seedType;
    }
}
