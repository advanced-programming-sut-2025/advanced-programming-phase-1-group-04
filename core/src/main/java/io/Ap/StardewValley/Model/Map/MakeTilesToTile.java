package io.Ap.StardewValley.Model.Map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MakeTilesToTile {
    private void shash() {
        TiledMap map = new TmxMapLoader().load("map.tmx");
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Ground");

        int width = layer.getWidth();
        int height = layer.getHeight();
        Tile[][] tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                String type = "void";
                boolean walkable = false;
                String item = "none";

                if (cell != null) {
                    TiledMapTile tile = cell.getTile();
                    MapProperties props = tile.getProperties();

                    if (props.containsKey("type"))
                        type = props.get("type", String.class);
                    if (props.containsKey("walkable"))
                        walkable = Boolean.parseBoolean(props.get("walkable", String.class));
                    if (props.containsKey("item"))
                        item = props.get("item", String.class);
                }

                //tiles[x][y] = new Tile(x, y, type, walkable, item);
            }
        }

    }
}
