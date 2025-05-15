package Model.Shop.PierresGeneralStore;

public enum PierresCraftRecipes {
    DehydratorRecipe("A recipe to make Dehydrator" , "Dehydrator (Recipe)" , 10000 , 1),
    GrassStarterRecipe("A recipe to make Grass Starter" , "Grass Starter (Recipe)" , 1000 , 1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PierresCraftRecipes(String description, String name, int price, int dailyLimit) {
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
