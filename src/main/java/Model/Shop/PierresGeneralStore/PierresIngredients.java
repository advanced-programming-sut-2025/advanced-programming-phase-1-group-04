package Model.Shop.PierresGeneralStore;

public enum PierresIngredients {
    Rice("A basic grain often served under vegetables." , "Rice" , 200 , Integer.MAX_VALUE),
    WheatFlour("A common cooking ingredient made from crushed wheat seeds." , "Wheat Flour" , 100 , Integer.MAX_VALUE),
    Sugar("Adds sweetness to pastries and candies. Too much can be unhealthy." , "Sugar" , 100 , Integer.MAX_VALUE),
    Oil("All purpose cooking oil." , "Oil" , 200 , Integer.MAX_VALUE),
    Vinegar("An aged fermented liquid used in many cooking recipes." , "Vinegar" , 200 , Integer.MAX_VALUE);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PierresIngredients(String description, String name, int price, int dailyLimit) {
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
