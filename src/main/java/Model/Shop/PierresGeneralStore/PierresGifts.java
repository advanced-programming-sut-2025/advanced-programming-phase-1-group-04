package Model.Shop.PierresGeneralStore;

public enum PierresGifts {
    Bouquet("A gift that shows your romantic interest.\n(Unlocked after reaching level 2 friendship with a player)" , "Bouquet" , 1000 , 2),
    WeddingRing("It's used to ask for another farmer's hand in marriage.\n(Unlocked after reaching level 3 friendship with a player)" , "Wedding Ring" , 10000 , 2);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PierresGifts(String description, String name, int price, int dailyLimit) {
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
