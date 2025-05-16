package Model.Cooking;

import Model.Map.Item;

import java.util.HashMap;

public enum FoodRecipe {
    FriedEgg("1 egg", null),
    BakedFish("1 sardine + 1 salmon + 1 wheat", null),
    Salad("1 leek + 1 dandelion", null),
    Omelet("1 egg + 1 milk", null),
    PumpkinPie("1 pumpkin + 1 wheat flour + 1 milk + 1 sugar", null),
    Spaghetti("1 wheat flour + 1 tomato", null),
    Pizza("1 wheat flour + 1 tomato + 1 cheese", null),
    Tortilla("1 corn", null),
    MakiRoll("1 any fish + 1 rice + 1 fiber", null),
    TripleShotEspresso("3 coffee", null),
    Cookie("1 wheat flour + 1 sugar + 1 egg", null),
    HashBrowns("1 potato + 1 oil", null),
    Pancakes("1 wheat flour + 1 egg", null),
    FruitSalad("1 blueberry + 1 melon + 1 apricot", null),
    RedPlate("1 red cabbage + 1 radish", null),
    Bread("1 wheat flour", null),
    SalmonDinner("1 salmon + 1 amaranth + 1 kale", null),
    VegetableMedley("1 tomato + 1 beet", null),
    FarmersLunch("1 omelet + 1 parsnip", null),
    SurvivalBurger("1 bread + 1 carrot + 1 eggplant", null),
    DishOTheSea("2 sardines + 1 hash browns", null),
    SeaformPudding("1 flounder + 1 midnight carp", null),
    MinersTreat("2 carrot + 1 sugar + 1 milk", null),
    TroutSoup ("", null);


    private final String recipeString;
    private final HashMap<Item, Integer> recipe;

    FoodRecipe(String recipeString, HashMap<Item, Integer> recipe) {
        this.recipeString = recipeString;
        this.recipe = recipe;
    }

    public String getRecipeString() {
        return recipeString;
    }

    public HashMap<Item, Integer> getRecipe() {
        return recipe;
    }
//  TODO:
//   private final HashMap <Item, Integer> = new HashMap<>();
}
