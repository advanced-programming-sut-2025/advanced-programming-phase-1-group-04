package Model.Trade.MarniesRanch;

public enum ShopInventory {
    Hay("Dried grass used as animal food." , "Hay" , 50 , -1),
    MilkPail("Gather milk from your animals." , "Milk Pail" , 1000 , 1),
    Shears("Use this to collect wool from sheep" , "Shears" , 1000 , 1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    ShopInventory(String description, String name, int price, int dailyLimit) {
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
