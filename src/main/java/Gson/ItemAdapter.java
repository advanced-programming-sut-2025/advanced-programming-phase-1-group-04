package Gson;

import Model.Map.Item;
import Model.Plants.ForagingCrop;
import Model.Plants.Sapling;
import Model.Plants.Seed;
import Model.Plants.Tree;
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
            case "Foraging Crop":
                return context.deserialize(jsonObject.get("data"), ForagingCrop.class);
            case "Seed":
                return context.deserialize(jsonObject.get("data"), Seed.class);
            default:
                throw new JsonParseException("Unknown type: " + type);
        }
    }
}
