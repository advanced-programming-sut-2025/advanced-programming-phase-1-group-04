package Model.Trade.PierresGeneralStore;

import Model.Time.Season;
import Model.Game;
import Model.Trade.ProductInterface;

public enum FallStock implements ProductInterface {
    EggplantSeeds("Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest." , "Eggplant Seeds" , 30 , 5),
    CornSeeds("Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest." , "Corn Seeds" , 225 , 5),
    PumpkinSeeds("Plant these in the fall. Takes 13 days to mature." , "Pumpkin Seeds" , 150 , 5),
    BokChoySeeds("Plant these in the fall. Takes 4 days to mature." , "Bok Choy Seeds" , 75 , 5),
    YamSeeds("Plant these in the fall. Takes 10 days to mature." , "Yam Seeds" , 90 , 5),
    CranberrySeeds("Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest." , "Cranberry Seeds" , 360 , 5),
    SunflowerSeeds("Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest." , "Sunflower Seeds" , 300 , 5),
    FairySeeds("Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors." , "Fairy Seeds" , 300 , 5),
    AmaranthSeeds("Plant these in the fall. Takes 7 days to grow. Harvest with the scythe." , "Amaranth Seeds" , 105 , 5),
    GrapeStarter("Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis." , "Grape Starter" , 90 , 5),
    WheatSeeds("Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe." , "Wheat Seeds" , 15 , 5),
    ArtichokeSeeds("Plant these in the fall. Takes 8 days to mature." , "Artichoke Seeds" , 45 , 5),;

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    FallStock(String description, String name, int price, int dailyLimit) {
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
        if (Game.getCurrentTime().getSeason() == Season.Fall)
            return (price * 2) / 3;
        else
            return price;
    }

    @Override
    public int getDailyLimit() {
        return dailyLimit;
    }
}
