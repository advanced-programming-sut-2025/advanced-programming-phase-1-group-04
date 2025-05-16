package Model.Crafting;

import Model.Cooking.Ingredient;
import Model.Cooking.IngredientType;
import Model.Map.Item;
import Model.Map.Stone;
import Model.Map.Wood;
import Model.Plants.ForagingMineral;
import Model.Plants.ForagingMineralType;

import java.util.HashMap;

public enum CraftRecipe {
    CherryBomb("4 copper ore + 1 coal", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Copper), 4);
        put(new ForagingMineral(ForagingMineralType.Coal), 1);
    }}),
    Bomb("4 iron ore + 1 coal", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Iron), 4);
        put(new ForagingMineral(ForagingMineralType.Coal), 1);
    }}),
    MegaBomb("4 gold ore + 1 coal", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Gold), 4);
        put(new ForagingMineral(ForagingMineralType.Coal), 1);
    }}),
    Sprinkler("1 copper bar + 1 iron bar", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Copper), 1);
        put(new ForagingMineral(ForagingMineralType.Iron), 1);
    }}),
    QualitySprinkler("1 iron bar + 1 gold bar", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Iron), 1);
        put(new ForagingMineral(ForagingMineralType.Gold), 1);
    }}),
    IridiumSprinkler("1 gold bar + 1 iridium bar", new HashMap<>() {{
        put(new ForagingMineral(ForagingMineralType.Gold), 1);
        put(new ForagingMineral(ForagingMineralType.Iriduim), 1);
    }}),
    CharcoalKiln("20 wood + 2 copper bar", new HashMap<>() {{
        put(new Wood(), 20);
        put(new ForagingMineral(ForagingMineralType.Copper), 1);
    }}),
    Furnace("20 copper ore + 25 stone", new HashMap<>() {{
        put(new Stone(), 25);
        put(new ForagingMineral(ForagingMineralType.Copper), 20);
    }}),
    Scarecrow("50 wood + 1 coal + 20 fiber", new HashMap<>() {{
        put(new Wood(), 50);
        put(new ForagingMineral(ForagingMineralType.Coal), 1);
        put(new Ingredient(IngredientType.Fiber), 20);
    }}),
    DeluxeScarecrow("50 wood + 1 coal + 20 fiber + 1 iridium ore", new HashMap<>() {{
        put(new Wood(), 50);
        put(new ForagingMineral(ForagingMineralType.Coal), 1);
        put(new Ingredient(IngredientType.Fiber), 20);
        put(new ForagingMineral(ForagingMineralType.Iriduim), 1);
    }}),
    BeeHouse("40 wood + 8 coal + 1 iron bar", new HashMap<>() {{
        put(new Wood(), 40);
        put(new ForagingMineral(ForagingMineralType.Coal), 8);
        put(new ForagingMineral(ForagingMineralType.Iron), 1);
    }}),
    CheesePress("45 wood + 45 stone + 1 copper bar", new HashMap<>() {{
        put(new Wood(), 45);
        put(new ForagingMineral(ForagingMineralType.Copper), 1);
        put(new Stone(), 45);
    }}),
    Keg("30 wood + 1 copper bar + 1 iron bar", new HashMap<>() {{
        put(new Wood(), 30);
        put(new ForagingMineral(ForagingMineralType.Copper), 1);
        put(new ForagingMineral(ForagingMineralType.Iron), 1);
    }}),
    Loom("60 wood + 30 fiber", new HashMap<>() {{
        put(new Wood(), 60);
        put(new Ingredient(IngredientType.Fiber), 30);
    }}),
    MayonnaiseMachine("15 wood + 15 stone + 1 copper bar", new HashMap<>() {{
        put(new Wood(), 15);
        put(new ForagingMineral(ForagingMineralType.Copper), 1);
        put(new Stone(), 15);
    }}),
    OilMaker("100 wood + 1 gold bar + 1 iron bar", new HashMap<>() {{
        put(new Wood(), 100);
        put(new ForagingMineral(ForagingMineralType.Gold), 1);
        put(new Ingredient(IngredientType.Fiber), 20);
    }}),
    PreservesJar("50 wood + 40 stone + 8 coal", new HashMap<>() {{
        put(new Wood(), 50);
        put(new ForagingMineral(ForagingMineralType.Coal), 8);
        put(new Stone(), 40);
    }}),
    Dehydrator("30 wood + 20 stone + 30 fiber", null),
    GrassStarter("1 wood + 1 fiber", null),
    FishSmoker("50 wood + 3 iron bar + 10 coal", null),
    MysticTreeSeed("5 acorn + 5 maple seed + 5 pine cone + 5 mahogany seed", null);

    private final String recipeString;
    private final HashMap<Item, Integer> recipe;
    CraftRecipe (String recipeString, HashMap<Item, Integer> recipe) {
        this.recipe = recipe;
        this.recipeString = recipeString;
    }

    public String getRecipeString() {
        return recipeString;
    }

    public HashMap<Item, Integer> getRecipe() {
        return recipe;
    }
}
