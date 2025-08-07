package io.Ap.StardewValley.Screen.ItemScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Plants.CropType;
import io.Ap.StardewValley.Model.Plants.ForagingCropType;
import io.Ap.StardewValley.Model.Plants.TreeType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ItemTextureBank {
    private final static Map<String, Texture> itemTextures = new HashMap<>();

    // Map Item:
    private static final Map<ForagingCropType, Texture> foragingCrops = new EnumMap<>(ForagingCropType.class);
    //private static final Texture stone = new Texture(Gdx.files.internal("stone.png"));
    //private static final Texture wood = new Texture(Gdx.files.internal("wood.png"));

    private static final Map<TreeType, List<Texture>> treeStages = new EnumMap<>(TreeType.class);
    private static final Map<CropType, List<Texture>> cropStages = new EnumMap<>(CropType.class);




    static {
        FileHandle dir = Gdx.files.internal("assets/inventory");
        for (FileHandle file : dir.list()) {
            if (!file.isDirectory() && file.extension().equals("png")) {
                String itemName = file.nameWithoutExtension();
                Texture texture = new Texture(file);
                itemTextures.put(itemName, texture);
            }
        }
    }

    public static void dispose() {
        for (Texture texture : itemTextures.values()) {
            texture.dispose();
        }
        itemTextures.clear();
    }

    public static Map<String, Texture> getItemTextures() {
        return itemTextures;
    }

    public static Texture getTexture (String itemName) {
        Texture tex;
        if ((tex = itemTextures.get(itemName.toLowerCase())) != null) {
            return tex;
        }
        return itemTextures.get("unknown");
    }

    public static Texture getTexture (Item item) {
        String itemName = item.getName();
        Texture tex;
        if ((tex = itemTextures.get(itemName.toLowerCase())) != null) {
            return tex;
        }
        return itemTextures.get("unknown");
    }
}
