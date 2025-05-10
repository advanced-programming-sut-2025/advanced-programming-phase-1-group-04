package Model.Trade.JojaMart;

import Model.Trade.ProductInterface;

public enum SummerStock implements ProductInterface {
    TomatoSeeds("Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest." , "Tomato Seeds" , 62 , 5),
    PepperSeeds("Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest." , "Pepper Seeds" , 50 , 5),
    WheatSeeds("Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe." , "Wheat Seeds" , 12 , 10),
    SummerSquashSeeds("Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest." , "Summer Squash Seeds" , 10 , 10),
    RadishSeeds("Plant these in the summer. Takes 6 days to mature." , "Radish Seeds" , 50 ,5),
    MelonSeeds("Plant these in the summer. Takes 12 days to mature." , "Melon Seeds" , 100 , 5),
    HopsStarter("Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis." , "Hops Starter" , 75 , 5),
    PoppySeeds("Plant in summer. Produces a bright red flower in 7 days." , "Poppy Seeds" , 125 , 5),
    SpangleSeeds("Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors." , "Spangle Seeds" , 62 , 5),
    StarfruitSeeds("Plant these in the summer. Takes 13 days to mature." , "Starfruit Seeds" , 400 , 5),
    CoffeeBeans("Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day." , "Coffee Beans" , 200 , 1),
    SunflowerSeeds("Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest." , "Sunflower Seeds" , 125 , 5);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    SummerStock(String description, String name, int price, int dailyLimit) {
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