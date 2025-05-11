package Model.Shop.JojaMart;

import Model.Shop.ProductInterface;

public enum SpringStock implements ProductInterface {
    ParsnipSeeds("Plant these in the spring. Takes 4 days to mature." , "Parsnip Seeds" , 25 ,5),
    BeanStarter("Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis." , "Bean Starter" , 75 , 5),
    CauliflowerSeeds("Plant these in the spring. Takes 12 days to produce a large cauliflower." , "Cauliflower Seeds" , 100 , 5),
    PotatoSeeds("Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest." , "Potato Seeds" , 62 , 5),
    StrawberrySeeds("Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that." , "Strawberry Seeds" , 100 , 5),
    TulipBulb("Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors." , "Tulip Bulb" , 25 , 5),
    KaleSeeds("Plant these in the spring. Takes 6 days to mature. Harvest with the scythe." , "Kale Seeds" , 87 , 5),
    CoffeeBeans("Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day." , "Coffee Beans" , 200 , 1),
    CarrotSeeds("Plant in the spring. Takes 3 days to grow." , "Carrot Seeds" , 5 , 10),
    RhubarbSeeds("Plant these in the spring. Takes 13 days to mature." , "Rhubarb Seeds" , 100 , 5),
    JazzSeeds("Plant in spring. Takes 7 days to produce a blue puffball flower." , "Jazz Seeds" , 37 , 5);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    SpringStock(String description, String name, int price, int dailyLimit) {
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
