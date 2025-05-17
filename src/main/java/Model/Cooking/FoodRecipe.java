package Model.Cooking;

import Model.Animals.AnimalProduct;
import Model.Animals.AnimalProductType;
import Model.Animals.Fish;
import Model.Animals.FishType;
import Model.Map.Item;
import Model.Plants.*;

import java.util.HashMap;

public enum FoodRecipe {
    FriedEgg("Fried egg", "1 egg", new HashMap<>() {{
        put(new AnimalProduct(AnimalProductType.Egg), 1);
    }}),
    BakedFish("Baked Fish", "1 sardine + 1 salmon + 1 wheat", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new Fish(FishType.Salmon), 1);
        put(new Fish(FishType.Sardine), 1);
    }}),
    Salad("Salad", "1 leek + 1 dandelion", new HashMap<>() {{
        put(new ForagingCrop(ForagingCropType.Leek), 1);
        put(new ForagingCrop(ForagingCropType.Dandelion), 1);
    }}),
    Omelet("Omelet", "1 egg + 1 cow milk", new HashMap<>() {{
        put(new AnimalProduct(AnimalProductType.Egg), 1);
        put(new AnimalProduct(AnimalProductType.CowMilk), 1);
    }}),
    PumpkinPie("Pumpkin pie", "1 pumpkin + 1 wheat flour + 1 cow milk + 1 sugar", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new Crop(CropType.Pumpkin), 1);
        put(new AnimalProduct(AnimalProductType.CowMilk), 1);
        put(new Ingredient(IngredientType.WheatFlour), 1);
    }}),
    Spaghetti("Spaghetti", "1 wheat flour + 1 tomato", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new Crop(CropType.Tomato), 1);
    }}),
    Pizza("Pizza", "1 wheat flour + 1 tomato + 1 cow milk", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new Crop(CropType.Tomato), 1);
        put(new AnimalProduct(AnimalProductType.CowMilk), 1);
    }}),
    Tortilla("Tortilla", "1 corn", new HashMap<>() {{
        put(new Crop(CropType.Corn), 1);
    }}),
    MakiRoll("Maki Roll", "1 Salmon + 1 rice + 1 fiber", new HashMap<>() {{
        put(new Ingredient(IngredientType.Rice), 1);
        put(new Ingredient(IngredientType.Fiber), 1);
        put(new Fish(FishType.Salmon), 1);
    }}),
    TripleShotEspresso("Triple Shot Espresso", "3 coffee", new HashMap<>() {{
        put(new Ingredient(IngredientType.Coffee), 1);
    }}),
    Cookie("Cookie", "1 wheat flour + 1 sugar + 1 egg", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new Ingredient(IngredientType.Sugar), 1);
        put(new AnimalProduct(AnimalProductType.Egg), 1);
    }}),
    HashBrowns("Hash Browns", "1 potato + 1 oil", new HashMap<>() {{
        put(new Ingredient(IngredientType.Oil), 1);
        put(new Crop(CropType.Potato), 1);
    }}),
    Pancakes("Pancakes", "1 wheat flour + 1 egg", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
        put(new AnimalProduct(AnimalProductType.Egg), 1);
    }}),
    FruitSalad("Fruit Salad", "1 blueberry + 1 melon + 1 apricot", new HashMap<>() {{
        put(new Fruit(FruitType.Apricot), 1);
        put(new Crop(CropType.Blueberry), 1);
        put(new Crop(CropType.Melon), 1);
    }}),
    RedPlate("Red Plate", "1 red cabbage + 1 radish", new HashMap<>() {{
        put(new Crop(CropType.RedCabbage), 1);
        put(new Crop(CropType.Radish), 1);
    }}),
    Bread("Bread", "1 wheat flour", new HashMap<>() {{
        put(new Ingredient(IngredientType.WheatFlour), 1);
    }}),
    SalmonDinner("Salmon Dinner", "1 salmon + 1 amaranth + 1 kale", new HashMap<>() {{
        put(new Crop(CropType.Kale), 1);
        put(new Fish(FishType.Salmon), 1);
        put(new Crop(CropType.Amaranth), 1);
    }}),
    VegetableMedley("Vegetable Medley", "1 tomato + 1 beet", new HashMap<>() {{
        put(new Crop(CropType.Tomato), 1);
        put(new Crop(CropType.Beet), 1);
    }}),
    FarmersLunch("Farmer's Lunch", "1 omelet + 1 parsnip", new HashMap<>() {{
        put(new Food(FoodType.Omelet), 1);
        put(new Crop(CropType.Parsnip), 1);
    }}),
    SurvivalBurger("Survival Burger", "1 bread + 1 carrot + 1 eggplant", new HashMap<>() {{
        put(new Food(FoodType.Bread), 1);
        put(new Crop(CropType.Carrot), 1);
        put(new Crop(CropType.Eggplant), 1);
    }}),
    DishOTheSea("Dish O' the Sea", "2 sardines + 1 hash browns", new HashMap<>() {{
        put(new Food(FoodType.HashBrowns), 1);
        put(new Fish(FishType.Sardine), 2);
    }}),
    SeaformPudding("Seaform Pudding", "1 flounder + 1 midnight carp", new HashMap<>() {{
        put(new Fish(FishType.MidnightCarp), 1);
        put(new Fish(FishType.Flounder), 1);
    }}),
    MinersTreat("Miner's Treat", "2 carrot + 1 sugar + 1 cow milk", new HashMap<>() {{
        put(new Ingredient(IngredientType.Sugar), 1);
        put(new Crop(CropType.Carrot), 1);
        put(new AnimalProduct(AnimalProductType.CowMilk), 1);
    }}),
    TroutSoup("Trout Soup", "", new HashMap<>());



    private final String name;
    private final String recipeString;
    private final HashMap<Item, Integer> recipe;

    FoodRecipe(String name, String recipeString, HashMap<Item, Integer> recipe) {
        this.name = name;
        this.recipeString = recipeString;
        this.recipe = recipe;
    }

    public String getRecipeString() {
        return recipeString;
    }

    public HashMap<Item, Integer> getRecipe() {
        return recipe;
    }

    public String getName() {
        return name;
    }
}
