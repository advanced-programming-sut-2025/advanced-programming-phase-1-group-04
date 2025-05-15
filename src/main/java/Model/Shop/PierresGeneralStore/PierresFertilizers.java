package Model.Shop.PierresGeneralStore;

public enum PierresFertilizers {
    DeluxeRetainingSoil("This soil has a 100% chance of staying watered overnight. Mix into tilled soil." , "Deluxe Retaining Soil" , 150 , Integer.MAX_VALUE),
    SpeedGro("Makes the plants grow 1 day earlier." , "Speed-Gro" , 100 , Integer.MAX_VALUE),
    BasicRetainingSoil("This soil has a chance of staying watered overnight. Mix into tilled soil." , "Basic Retaining Soil" , 100 , Integer.MAX_VALUE),
    QualityRetainingSoil("This soil has a good chance of staying watered overnight. Mix into tilled soil." , "Quality Retaining Soil" , 150 , Integer.MAX_VALUE);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PierresFertilizers(String description, String name, int price, int dailyLimit) {
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
