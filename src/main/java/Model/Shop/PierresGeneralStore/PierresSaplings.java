package Model.Shop.PierresGeneralStore;

public enum PierresSaplings {
    AppleSapling("Takes 28 days to produce a mature Apple tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty." , "Apple Sapling" , 4000 , Integer.MAX_VALUE),
    ApricotSapling("Takes 28 days to produce a mature Apricot tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty." , "Apricot Sapling" , 2000 , Integer.MAX_VALUE),
    CherrySapling("Takes 28 days to produce a mature Cherry tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty." , "Cherry Sapling" , 3400 , Integer.MAX_VALUE),
    OrangeSapling("Takes 28 days to produce a mature Orange tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty." , "Orange Sapling" , 4000 , Integer.MAX_VALUE),
    PeachSapling("Takes 28 days to produce a mature Peach tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty." , "Peach Sapling" , 6000 , Integer.MAX_VALUE),
    PomegranateSapling("Takes 28 days to produce a mature Pomegranate tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty." , "Pomegranate Sapling" , 6000 , Integer.MAX_VALUE);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PierresSaplings(String description, String name, int price, int dailyLimit) {
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
