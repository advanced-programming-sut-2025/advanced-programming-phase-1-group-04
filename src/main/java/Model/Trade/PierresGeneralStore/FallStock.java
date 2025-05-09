package Model.Trade.PierresGeneralStore;

public enum FallStock {
    EggplantSeeds("Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest." , "Eggplant Seeds" , 30 , 20 , 5),
    CornSeeds("Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest." , "Corn Seeds" , 225 , 150 , 5),
    PumpkinSeeds("Plant these in the fall. Takes 13 days to mature." , "Pumpkin Seeds" , 150 , 100 , 5),
    BokChoySeeds("Plant these in the fall. Takes 4 days to mature." , "Bok Choy Seeds" , 75 , 50 , 5),
    YamSeeds("Plant these in the fall. Takes 10 days to mature." , "Yam Seeds" , 90 , 60 , 5),
    CranberrySeeds("Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest." , "Cranberry Seeds" , 360 , 240 , 5),
    SunflowerSeeds("Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest." , "Sunflower Seeds" , 300 , 200 , 5),
    FairySeeds("Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors." , "Fairy Seeds" , 300 , 200 , 5),
    AmaranthSeeds("Plant these in the fall. Takes 7 days to grow. Harvest with the scythe." , "Amaranth Seeds" , 105 , 70 , 5),
    GrapeStarter("Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis." , "Grape Starter" , 90 , 60 , 5),
    WheatSeeds("Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe." , "Wheat Seeds" , 15 , 10 , 5),
    ArtichokeSeeds("Plant these in the fall. Takes 8 days to mature." , "Artichoke Seeds" , 45 , 30 , 5),;

    private final String description;
    private final String name;
    private final int price;
    private final int seasonPrice;
    private final int dailyLimit;

    FallStock(String description, String name, int price, int seasonPrice, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.seasonPrice = seasonPrice;
        this.dailyLimit = dailyLimit;
    }
}
