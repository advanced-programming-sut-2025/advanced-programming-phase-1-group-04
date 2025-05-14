package Model.Cooking;

import Model.Map.Item;

public class Ingredient implements Item {
    private final IngredientType type;

    public Ingredient (IngredientType type) {
        this.type = type;
    }
    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        return type.getSellPrice();
    }

    public IngredientType getType() {
        return type;
    }
}
