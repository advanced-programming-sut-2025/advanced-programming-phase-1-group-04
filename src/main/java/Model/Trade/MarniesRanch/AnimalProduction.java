package Model.Trade.MarniesRanch;

public enum AnimalProduction {
    Chicken("Well cared-for chickens lay eggs every day. Lives in the coop." , 800 , "Coop" , 2),
    Cow("Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn." , 1500 , "Barn" , 2),
    Goat("Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn." , 4000 , "BigBarn" , 2),
    Duck("Happy lay duck eggs every other day. Lives in the coop." , 1200 , "BigCoop" , 2),
    Sheep("Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn." , 8000 , "DeluxeBarn" , 2),
    Rabbit("These are wooly rabbits! They shed precious wool every few days. Lives in the coop." , 8000 , "DeluxeCoop" , 2),
    Dinosaur("The Dinosaur is a farm animal that lives in a Big Coop" , 14000 , "BigCoop" , 2),
    Pig("These pigs are trained to find truffles! Lives in the barn." , 16000 , "DeluxeBarn" , 2);

    private final String description;
    private final int price;
    private final String buildingRequired;
    private final int dailyLimit;

    AnimalProduction(String description, int price, String buildingRequired, int dailyLimit) {
        this.description = description;
        this.price = price;
        this.buildingRequired = buildingRequired;
        this.dailyLimit = dailyLimit;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getBuildingRequired() {
        return buildingRequired;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
