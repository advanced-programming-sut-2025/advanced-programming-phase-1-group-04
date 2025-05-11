package Model.Cooking;

import Model.Player.*;
import Model.Shop.ShopType;

import java.util.HashMap;

public class Food {
    private final FoodType type;

    public Food (FoodType type) {
        this.type = type;
    }

    public FoodType getType() {
        return type;
    }

    public String getName() {
        return type.getName();
    }

    public FoodRecipe getRecipe() {
        return type.getRecipe();
    }

    public HashMap<Skill, Integer> getLevel() {
        return type.getLevel();
    }

    public ShopType getShop() {
        return type.getShop();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public int getSellPrice() {
        return type.getSellPrice();
    }

    public void eat (Player player) {
        player.addEnergy(type.getEnergy());
        type.applyBuff(player);
    }
}
