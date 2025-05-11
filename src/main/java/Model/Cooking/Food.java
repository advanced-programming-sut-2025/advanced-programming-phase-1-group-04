package Model.Cooking;

import Model.Map.Item;
import Model.Player.*;
import Model.Shop.ShopType;

import java.util.HashMap;

public class Food implements Item {
    private final FoodType type;

    public Food (FoodType type) {
        this.type = type;
    }

    public FoodType getType() {
        return type;
    }

    @Override
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

    @Override
    public int getPrice() {
        return type.getSellPrice();
    }

    public void eat (Player player) {
        player.addEnergy(type.getEnergy());
        type.applyBuff(player);
    }
}
