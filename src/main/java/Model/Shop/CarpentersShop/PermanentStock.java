package Model.Shop.CarpentersShop;

public enum PermanentStock {
    Wood("A sturdy, yet flexible plant material with a wide variety of uses." , 10 , -1),
    Stone("A common material with many uses in crafting and building." , 20 , -1);

    private final String description;
    private final int price;
    private final int dailyLimit;

    PermanentStock(String description, int price, int dailyLimit) {
        this.description = description;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
