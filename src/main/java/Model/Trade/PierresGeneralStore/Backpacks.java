package Model.Trade.PierresGeneralStore;

public enum Backpacks {
    LargePack("Unlocks the 2nd row of inventory (12 more slots, total 24)." , "Large Pack" , 2000 , 1),
    DeluxePack("Unlocks the 3rd row of inventory (infinite slots)." , "Deluxe Pack" , 10000 , 1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    Backpacks(String description, String name, int price, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }
}
