package Model.Shop.PierresGeneralStore;

import Model.App;
import Model.Time.Season;

public enum SummerSeeds {
    GrassStarter("Place this on your farm to start a new patch of grass." , "Grass Starter" , 100 , Integer.MAX_VALUE),
    MelonSeeds("Plant these in the summer. Takes 12 days to mature." , "Melon Seeds" , 120 , 5),
    TomatoSeeds("Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest." , "Tomato Seeds" , 75 , 5),
    BlueberrySeeds("Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest." , "Blueberry Seeds" , 120 , 5),
    PepperSeeds("Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest." , "Pepper Seeds" , 60 , 5),
    WheatSeeds("Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe." , "Wheat Seeds", 15 , 5),
    RadishSeeds("Plant these in the summer. Takes 6 days to mature." , "Radish Seeds" , 60 , 5),
    PoppySeeds("Plant in summer. Produces a bright red flower in 7 days." , "Poppy Seeds" , 150 , 5),
    SpangleSeeds("Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors." , "Spangle Seeds" , 75 , 5),
    HopsStarter("Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis." , "Hops Starter" , 90 , 5),
    CornSeeds("Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest." , "Corn Seeds" , 225 , 5),
    SunflowerSeeds("Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest." , "Sunflower Seeds" , 300 , 5),
    RedCabbageSeeds("Plant these in the summer. Takes 9 days to mature." , "Red Cabbage Seeds" , 150 , 5);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    SummerSeeds(String description, String name, int price, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }


    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }


    public int getPrice() {
        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Summer)
            return (price * 2) / 3;
        else
            return price;
    }


    public int getDailyLimit() {
        return dailyLimit;
    }
}
