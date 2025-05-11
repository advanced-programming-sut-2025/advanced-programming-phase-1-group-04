package Model.Trade.CarpentersShop;

public enum FarmBuildings {
    Barn("Houses 4 barn-dwelling animals." , "Barn" , 6000 , 350 , 150 , 7 , 4 , 1),
    BigBarn("Houses 8 barn-dwelling animals. Unlocks goats." , "Big Barn" , 12000 , 450 , 200 , 7 , 4 , 1),
    DeluxeBarn("Houses 12 barn-dwelling animals. Unlocks sheep and pigs." , "Deluxe Barn" , 25000 , 550 , 300 , 7 , 4 , 1),
    Coop("Houses 4 coop-dwelling animals." , "Coop" , 4000 , 300 , 100 , 6 , 3 , 1),
    BigCoop("Houses 8 coop-dwelling animals. Unlocks ducks." , "Big Coop" , 10000 , 400 , 150 , 6 , 3 , 1),
    DeluxeCoop("Houses 12 coop-dwelling animals. Unlocks rabbits." , "Deluxe Coop" , 20000 , 500 , 200 , 6 , 3 , 1),
    Well("Provides a place for you to refill your watering can." , "Well" , 1000 , 0 , 75 , 3 , 3 , 1),
    ShippingBin("Items placed in it will be included in the nightly shipment." , "Shipping Bin" , 250 , 150 , 0 , 1 , 1 , -1);

    private final String description;
    private final String name;
    private final int cost;
    private final int woodCost;
    private final int stonesCost;
    private final int l;
    private final int w;
    private final int dailyLimit;

    FarmBuildings(String description, String name, int cost, int woodCost, int stonesCost, int l, int w, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.cost = cost;
        this.woodCost = woodCost;
        this.stonesCost = stonesCost;
        this.l = l;
        this.w = w;
        this.dailyLimit = dailyLimit;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public int getStonesCost() {
        return stonesCost;
    }

    public int getL() {
        return l;
    }

    public int getW() {
        return w;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }
}
