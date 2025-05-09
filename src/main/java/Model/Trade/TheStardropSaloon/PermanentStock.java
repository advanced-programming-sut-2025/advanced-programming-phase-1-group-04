package Model.Trade.TheStardropSaloon;

public enum PermanentStock {
    Beer("Drink in moderation." , "Beer" , 400 , -1),
    Salad("A healthy garden salad." , "Salad" ,  220 , -1),
    Bread("A crusty baguette." , "Bread" , 120 , -1),
    Spaghetti("An old favorite." , "Spaghetti" ,240 , -1),
    Pizza("It's popular for all the right reasons." , "Pizza" , 600 , -1),
    Coffee("It smells delicious. This is sure to give you a boost." , "Coffee" ,300 , -1),
    HashbrownsRecipe("A recipe to make Hashbrowns" , "Hashbrowns Recipe" , 50 , 1),
    OmeletRecipe("A recipe to make Omelet" , "Omelet Recipe" , 100 , 1),
    PancakesRecipe("A recipe to make Pancakes" , "Pancakes Recipe" , 100 , 1),
    BreadRecipe("A recipe to make Bread" , "Bread Recipe" , 100 , 1),
    TortillaRecipe("A recipe to make Tortilla" , "Tortilla Recipe" , 100 , 1),
    PizzaRecipe("A recipe to make Pizza" , "Pizza Recipe" , 150 , 1),
    MakiRollRecipe("A recipe to make Maki Roll" , "Maki Roll Recipe" , 300 , 1),
    TripleShotEspressoRecipe("A recipe to make Triple Shot Espresso" , "Triple Shot Espresso Recipe" , 5000 , 1),
    CookieRecipe("A recipe to make Cookie" , "Cookie Recipe" , 300 , 1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    PermanentStock(String description, String name, int price, int dailyLimit) {
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
