package Model.Trade.PierresGeneralStore;

public enum SpringStock {
    ParsnipSeeds("Plant these in the spring. Takes 4 days to mature." , "Parsnip Seeds" , 30 , 20 , 5),
    BeanStarter("Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis." , "Bean Starter" , 90 , 60 , 5),
    CauliflowerSeeds("Plant these in the spring. Takes 12 days to produce a large cauliflower." , "Cauliflower Seeds" , 120 , 80 , 5),
    PotatoSeeds("Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest." , "Potato Seeds" , 75 , 50 , 5),
    TulipBulb("Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors." , "Tulip Bulb" , 30 , 20 , 5),
    KaleSeeds("Plant these in the spring. Takes 6 days to mature. Harvest with the scythe." , "Kale Seeds" , 105 , 70 , 5),
    JazzSeeds("Plant in spring. Takes 7 days to produce a blue puffball flower." , "Jazz Seeds" , 45 , 30 , 5),
    GarlicSeeds("Plant these in the spring. Takes 4 days to mature." , "Garlic Seeds" , 60 , 40 , 5),
    RiceShoot("Plant these in the spring. Takes 8 days to mature. Grows faster if planted near a body of water. Harvest with the scythe." , "Rice Shoot" , 60 , 40 , 5);

    private final String description;
    private final String name;
    private final int price;
    private final int seasonPrice;
    private final int dailyLimit;

    SpringStock(String description, String name, int price, int seasonPrice, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.seasonPrice = seasonPrice;
        this.dailyLimit = dailyLimit;
    }
}
