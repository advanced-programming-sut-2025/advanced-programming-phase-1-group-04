package Model.Shop.JojaMart;

import Model.Cooking.IngredientType;
import Model.Shop.ProductInterface;

public enum JojaIngredients implements ProductInterface {
    JojaCola(IngredientType.JojaCola,"The flagship product of Joja corporation." ,"Joja Cola" , 75 , Integer.MAX_VALUE),
    Sugar(IngredientType.Sugar, "Adds sweetness to pastries and candies. Too much can be unhealthy." , "Sugar" , 125 , Integer.MAX_VALUE),
    WheatFlour(IngredientType.WheatFlour, "A common cooking ingredient made from crushed wheat seeds." , "Wheat Flour", 125 , Integer.MAX_VALUE),
    Rice(IngredientType.Rice, "A basic grain often served under vegetables." , "Rice", 250 , Integer.MAX_VALUE);

    private final IngredientType ingredientType;
    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    JojaIngredients(IngredientType ingredientType, String description, String name, int price, int dailyLimit) {
        this.ingredientType = ingredientType;
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getDailyLimit() {
        return dailyLimit;
    }

    public IngredientType getType() {
        return ingredientType;
    }
}
