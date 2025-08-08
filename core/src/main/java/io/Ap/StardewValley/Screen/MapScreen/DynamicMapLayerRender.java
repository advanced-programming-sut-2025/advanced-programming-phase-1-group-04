package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.*;

import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Item.Stone;
import io.Ap.StardewValley.Model.Item.Wood;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.Model.Plants.*;
import io.Ap.StardewValley.StardewValley;

public class DynamicMapLayerRender {
    private final Map<String, TextureRegion> additional = new HashMap<>();

    // Map Item:
    private final Map<ForagingCropType, TextureRegion> foragingCrops = new EnumMap<>(ForagingCropType.class);

    private final Map<TreeType, List<TextureRegion>> treeStages = new EnumMap<>(TreeType.class);
    private final Map<CropType, List<TextureRegion>> cropStages = new EnumMap<>(CropType.class);

    {
        TextureRegion[][] treeSheet1 = TextureRegion.split(new Texture("map/items/TreeStages.png"), 48, 5 * 16);
        TextureRegion[][] treeSheet2 = TextureRegion.split(new Texture("map/items/TreeStages2.png"), 48, 6 * 16);

        TreeType[] treeTypes = TreeType.values();

        for (int i = 0; i < 14; i++) {
            TreeType type = treeTypes[i];
            List<TextureRegion> stages;
            if (i < 8)
                stages = new ArrayList<>(Arrays.asList(treeSheet1[i]).subList(0, 5));
            else
                stages = new ArrayList<>(Arrays.asList(treeSheet2[i-8]).subList(0, 5));

            treeStages.put(type, stages);
        }
    }
    {
        TextureRegion[][] cropSheet = TextureRegion.split(new Texture("map/items/CropStages.png"), 16, 32);

        CropType[] cropTypes = CropType.values();

        for (int i = 0; i < cropTypes.length - 1; i++) {
            CropType type = cropTypes[i];
            List<TextureRegion> stages;
            int len = type.getStages().length + 1;

            if (i < 21)
                stages = new ArrayList<>(Arrays.asList(cropSheet[i]).subList(0, len));
            else
                stages = new ArrayList<>(Arrays.asList(cropSheet[i-21]).subList(6, 6 + len));

            cropStages.put(type, stages);
        }
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

    public void renderGround() {
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
            }
        }
    }

    public void renderItem() {
        int tileSize = 16;
        Tile[][] tiles = App.getGame().getMap().getCurrentRegion().getTiles();

        SpriteBatch batch = StardewValley.getBatch();

        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                Tile tile = tiles[y][x];

                float drawX = x * tileSize;
                float drawY = (tiles.length - 1 - y) * tileSize;

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
                    else if (item instanceof Crop crop) {
                        int stageIndex = crop.getCurrentStage() - 1;
                        List<TextureRegion> stages = cropStages.get(crop.getType());
                        batch.draw(stages.get(stageIndex), drawX, drawY);
                    }
                    else if (item instanceof Tree tree) {
                        int stageIndex = tree.getCurrentStage() - 1;
                        List<TextureRegion> stages = treeStages.get(tree.getType());
                        batch.draw(stages.get(stageIndex), drawX, drawY);
                    }
                }
            }
        }
    }
}
