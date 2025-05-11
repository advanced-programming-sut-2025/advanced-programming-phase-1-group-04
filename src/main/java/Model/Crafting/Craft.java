package Model.Crafting;

import Model.Map.Item;
import Model.Player.*;
import Model.Shop.ShopType;

import java.util.HashMap;

public class Craft implements Item {
    private final CraftType type;

    public Craft (CraftType type) {
        this.type = type;
    }

    public CraftType getType() {
        return type;
    }

    public CraftRecipe getIngredient() {
        return type.getIngredient();
    }

    public HashMap<Skill, Integer> getLevel() {
        return type.getLevel();
    }

    public ShopType getShop() {
        return type.getShop();
    }

    @Override
    public int getPrice() {
        return type.getSellPrice();
    }

    @Override
    public String getName() {
        return type.getName();
    }
}
