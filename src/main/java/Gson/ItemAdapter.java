package Gson;

import Model.Animals.AnimalProduct;
import Model.Animals.Fish;
import Model.Cooking.Food;
import Model.Cooking.Ingredient;
import Model.Crafting.Craft;
import Model.Map.Item;
import Model.Map.Stone;
import Model.Map.Wood;
import Model.Plants.*;
import Model.Player.GiftItem;
import Model.Tool.Tool;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ItemAdapter implements JsonSerializer<Item>, JsonDeserializer<Item> {

    @Override
    public JsonElement serialize(Item src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("typeGson", src.getClass().getSimpleName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("typeGson").getAsString();

        switch (type) {
            case "Tree":
                return context.deserialize(jsonObject.get("data"), Tree.class);
            case "Sapling":
                return context.deserialize(jsonObject.get("data"), Sapling.class);
            case "Crop":
                return context.deserialize(jsonObject.get("data"), Crop.class);
            case "Fruit":
                return context.deserialize(jsonObject.get("data"), Fruit.class);
            case "Foraging mineral":
                return context.deserialize(jsonObject.get("data"), ForagingMineral.class);
            case "Foraging Crop":
                return context.deserialize(jsonObject.get("data"), ForagingCrop.class);
            case "Seed":
                return context.deserialize(jsonObject.get("data"), Seed.class);

            case "Stone":
                return context.deserialize(jsonObject.get("data"), Stone.class);
            case "Wood":
                return context.deserialize(jsonObject.get("data"), Wood.class);

            case "Craft":
                return context.deserialize(jsonObject.get("data"), Craft.class);
            case "Food":
                return context.deserialize(jsonObject.get("data"), Food.class);

            case "Fish":
                return context.deserialize(jsonObject.get("data"), Fish.class);
            case "Animal Produce":
                return context.deserialize(jsonObject.get("data"), AnimalProduct.class);

            case "Ingredient":
                return context.deserialize(jsonObject.get("data"), Ingredient.class);
            case "Fertilizer":
                return context.deserialize(jsonObject.get("data"), Fertilizer.class);
            case "Gift":
                return context.deserialize(jsonObject.get("data"), GiftItem.class);
            case "Tool":
                return context.deserialize(jsonObject.get("data"), Tool.class);


            default:
                throw new JsonParseException("Unknown type: " + type);
        }
    }
}
