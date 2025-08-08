package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Item.Stone;
import io.Ap.StardewValley.Model.Item.Wood;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.Model.Plants.CropType;
import io.Ap.StardewValley.Model.Plants.ForagingCrop;
import io.Ap.StardewValley.Model.Plants.ForagingCropType;
import io.Ap.StardewValley.Model.Plants.TreeType;
import io.Ap.StardewValley.StardewValley;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DynamicMapLayerRender {
    private final Map<String, TextureRegion> additional = new HashMap<>();

    // Map Item:
    private final Map<ForagingCropType, TextureRegion> foragingCrops = new EnumMap<>(ForagingCropType.class);

    private final Map<TreeType, List<Texture>> treeStages = new EnumMap<>(TreeType.class);
    private final Map<CropType, List<Texture>> cropStages = new EnumMap<>(CropType.class);

    {
        TextureRegion[][] cropSheet = TextureRegion.split(new Texture("map/items/ForagingCrops.png"), 16, 16);

    }
    {
        TextureRegion[][] foragingSheet = TextureRegion.split(new Texture("map/items/ForagingCrops.png"), 16, 16);

        int rows = foragingSheet.length;
        int cols = foragingSheet[0].length;

        int index = 0;
        for (ForagingCropType type : ForagingCropType.values()) {
            int row = index / cols;
            int col = index % cols;

            if (row >= rows) break;

            foragingCrops.put(type, foragingSheet[row][col]);
            index++;
        }
    }

    {
        TextureRegion[][] tiledSheet = TextureRegion.split(new Texture("map/tiles/hoeDirt.png"), 16, 16);
        additional.put("plowed", tiledSheet[1][2]);
        additional.put("fertilized", tiledSheet[1][6]);
        additional.put("watered", tiledSheet[1][10]);

        additional.put("shadow", new TextureRegion(new Texture("etc/shadow.png")));

        TextureRegion[][] itemSheet = TextureRegion.split(new Texture("map/items/additional.png"), 16, 16);

        additional.put("stone", itemSheet[0][0]);
        additional.put("stone2", itemSheet[0][1]);
        additional.put("wood", itemSheet[0][2]);
        additional.put("wood2", itemSheet[0][3]);

    }

    public void render() {
        // for plowed, fertilized
        int tileSize = 16;
        Tile[][] tiles = App.getGame().getMap().getCurrentRegion().getTiles();

        SpriteBatch batch = StardewValley.getBatch();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                Tile tile = tiles[y][x];

                float drawX = x * tileSize;
                float drawY = (tiles.length - 1 - y) * tileSize;

                if (tile.isPlowed())
                    batch.draw(additional.get("plowed"), drawX, drawY);

                if (tile.getFertilize() != 0)
                    batch.draw(additional.get("fertilized"), drawX, drawY);

                if (tile.isWatered())
                    batch.draw(additional.get("watered"), drawX, drawY);

                Item item = tile.getItem();
                if (item != null) {
                    if (item instanceof ForagingCrop foragingCrop) {
                        batch.draw(additional.get("shadow"), drawX, drawY);
                        batch.draw(foragingCrops.get(foragingCrop.getType()), drawX, drawY);
                    }
                    else if (item instanceof Stone) {
                        batch.draw(additional.get("stone"), drawX, drawY);
                    }
                    else if (item instanceof Wood) {
                        batch.draw(additional.get("wood"), drawX, drawY);
                    }
                }
            }
        }
    }
}
