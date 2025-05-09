package Model.Trade;

public enum ShopType {
    Blacksmith("Clint" , "Blacksmith" , 9 , 16),
    JojaMart("Morris" , "JojaMart" , 9 , 23),
    PierresGeneralStore("Pierre" , "Pierre's General Store" , 9 , 17),
    CarpentersShop("Robin" , "Carpenter's Shop" , 9 , 20),
    FishShop("Willy" , "Fish Shop" , 9 , 17),
    MarniesRanch("Marnie" , "Marnie's Ranch" , 9 , 16),
    TheStarDropSaloon("Gus" , "The StarDrop Saloon" , 12 , 24);

    private final String owner;
    private final String name;
    private final int startingHour;
    private final int finishingHour;

    ShopType(String owner, String name, int startingHour, int finishingHour) {
        this.owner = owner;
        this.name = name;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public int getStartingHour() {
        return startingHour;
    }

    public int getFinishingHour() {
        return finishingHour;
    }
}
