package Model.Crafting;

import Model.Player.*;
import Model.Shop.ShopType;

import java.util.HashMap;

public class Craft {
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

    public int getSellPrice() {
        return type.getSellPrice();
    }

    public String getName() {
        return type.getName();
    }
}
