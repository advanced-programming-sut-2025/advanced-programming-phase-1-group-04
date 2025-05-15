package Model.Shop.PierresGeneralStore;

public enum WinterSeeds {
    GrassStarter("Place this on your farm to start a new patch of grass." , "Grass Starter" , 100 , Integer.MAX_VALUE);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    WinterSeeds(String description, String name, int price, int dailyLimit) {
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
