package Model.Trade.JojaMart;

public enum FallStock {
    CornSeeds("Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest." , "Corn Seeds" , 187 , 5),
    EggplantSeeds("Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest." , "Eggplant Seeds", 25 , 5),
    PumpkinSeeds("Plant these in the fall. Takes 13 days to mature." , "Pumpkin Seeds" , 125 , 5),
    BroccoliSeeds("Plant in the fall. Takes 8 days to mature, and continues to produce after first harvest." , "Broccoli Seeds" , 15 , 5),
    AmaranthSeeds("Plant these in the fall. Takes 7 days to grow. Harvest with the scythe." , "Amaranth Seeds" , 87 , 5),
    GrapeStarter("Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis." , "Grape Starter" , 75 , 5),
    BeetSeeds("Plant these in the fall. Takes 6 days to mature." , "Beet Seeds" , 20 , 5),
    YamSeeds("Plant these in the fall. Takes 10 days to mature." , "Yam Seeds" , 75 , 5),
    BokChoySeeds("Plant these in the fall. Takes 4 days to mature." , "Bok Choy Seeds" , 62 , 5),
    CranberrySeeds("Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest." , "Cranberry Seeds" , 300 , 5),
    SunflowerSeeds("Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest." , "Sunflower Seeds" , 125 , 5),
    FairySeeds("Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors." , "Fairy Seeds" , 250 , 5),
    RareSeed("Sow in fall. Takes all season to grow." , "Rare Seed" , 1000 , 1),
    WheatSeeds("Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe." , "Wheat Seeds" , 12 , 5);

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

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
