package Model.Shop.PierresGeneralStore;

import Model.Shop.ProductInterface;

public enum YearRoundStock implements ProductInterface {
    Rice("A basic grain often served under vegetables." , "Rice" , 200 , -1),
    WheatFlour("A common cooking ingredient made from crushed wheat seeds." , "Wheat Flour" , 100 , -1),
    Bouquet("A gift that shows your romantic interest.\n(Unlocked after reaching level 2 friendship with a player)" , "Bouquet" , 1000 , 2),
    WeddingRing("It's used to ask for another farmer's hand in marriage.\n(Unlocked after reaching level 3 friendship with a player)" , "Wedding Ring" , 10000 , 2),
    DehydratorRecipe("A recipe to make Dehydrator" , "Dehydrator (Recipe)" , 10000 , 1),
    GrassStarterRecipe("A recipe to make Grass Starter" , "Grass Starter (Recipe)" , 1000 , 1),
    Sugar("Adds sweetness to pastries and candies. Too much can be unhealthy." , "Sugar" , 100 , -1),
    Oil("All purpose cooking oil." , "Oil" , 200 , -1),
    Vinegar("An aged fermented liquid used in many cooking recipes." , "Vinegar" , 200 , -1),
    DeluxeRetainingSoil("This soil has a 100% chance of staying watered overnight. Mix into tilled soil." , "Deluxe Retaining Soil" , 150 , -1),
    GrassStarter("Place this on your farm to start a new patch of grass." , "Grass Starter" , 100 , -1),
    SpeedGro("Makes the plants grow 1 day earlier." , "Speed-Gro" , 100 , -1),
    AppleSapling("Takes 28 days to produce a mature Apple tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty." , "Apple Sapling" , 4000 , -1),
    ApricotSapling("Takes 28 days to produce a mature Apricot tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty." , "Apricot Sapling" , 2000 , -1),
    CherrySapling("Takes 28 days to produce a mature Cherry tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty." , "Cherry Sapling" , 3400 , -1),
    OrangeSapling("Takes 28 days to produce a mature Orange tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty." , "Orange Sapling" , 4000 , -1),
    PeachSapling("Takes 28 days to produce a mature Peach tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty." , "Peach Sapling" , 6000 , -1),
    PomegranateSapling("Takes 28 days to produce a mature Pomegranate tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty." , "Pomegranate Sapling" , 6000 , -1),
    BasicRetainingSoil("This soil has a chance of staying watered overnight. Mix into tilled soil." , "Basic Retaining Soil" , 100 , -1),
    QualityRetainingSoil("This soil has a good chance of staying watered overnight. Mix into tilled soil." , "Quality Retaining Soil" , 150 , -1),;

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    YearRoundStock(String description, String name, int price, int dailyLimit) {
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
