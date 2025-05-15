package Model.Shop.PierresGeneralStore;

import Model.App;
import Model.Time.Season;

public enum SpringSeeds {
    GrassStarter("Place this on your farm to start a new patch of grass." , "Grass Starter" , 100 , Integer.MAX_VALUE),
    ParsnipSeeds("Plant these in the spring. Takes 4 days to mature." , "Parsnip Seeds" , 30 , 5),
    BeanStarter("Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis." , "Bean Starter" , 90 , 5),
    CauliflowerSeeds("Plant these in the spring. Takes 12 days to produce a large cauliflower." , "Cauliflower Seeds" , 120 , 5),
    PotatoSeeds("Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest." , "Potato Seeds", 50 , 5),
    TulipBulb("Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors." , "Tulip Bulb" , 30 , 5),
    KaleSeeds("Plant these in the spring. Takes 6 days to mature. Harvest with the scythe." , "Kale Seeds" , 105 , 5),
    JazzSeeds("Plant in spring. Takes 7 days to produce a blue puffball flower." , "Jazz Seeds" , 45 , 5),
    GarlicSeeds("Plant these in the spring. Takes 4 days to mature." , "Garlic Seeds" , 60 , 5),
    RiceShoot("Plant these in the spring. Takes 8 days to mature. Grows faster if planted near a body of water. Harvest with the scythe." , "Rice Shoot" , 60 , 5);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    SpringSeeds(String description, String name, int price, int dailyLimit) {
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
        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Spring)
            return (price * 2) / 3;
        else
            return price;
    }


    public int getDailyLimit() {
        return dailyLimit;
    }
}
