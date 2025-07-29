package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.StardewValley;

public class TiledMapRendererHelper {
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer renderer;

    private final Array<MapLayer> backgroundLayers = new Array<>();
    private final Array<MapLayer> frontLayers = new Array<>();

    public TiledMapRendererHelper(String pathToTmx) {
        tiledMap = new TmxMapLoader().load(pathToTmx);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        for (MapLayer layer : tiledMap.getLayers()) {
            if (layer.getName().toLowerCase().contains("front")) {
                frontLayers.add(layer);
            } else {
                backgroundLayers.add(layer);
            }
        }

        //shash();
    }

    public SpriteBatch getBatch() {
        return (SpriteBatch) renderer.getBatch();
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void renderBeforePlayer(OrthographicCamera camera) {
        StardewValley.getBatch().begin();
        renderer.setView(camera);
        for (MapLayer layer : backgroundLayers)
            renderer.renderTileLayer((TiledMapTileLayer) layer);
        StardewValley.getBatch().end();
    }

    public void renderAfterPlayer(OrthographicCamera camera) {
        StardewValley.getBatch().begin();
        renderer.setView(camera);
        for (MapLayer layer : frontLayers)
            renderer.renderTileLayer((TiledMapTileLayer) layer);
        StardewValley.getBatch().end();
    }


    public void dispose() {
        tiledMap.dispose();
        renderer.dispose();
    }

    private void shash() {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Back");

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

                    if (props.containsKey("Type"))
                        type = props.get("Type", String.class);
                    //if (props.containsKey("walkable"))
                    //    walkable = Boolean.parseBoolean(props.get("walkable", String.class));
                    //if (props.containsKey("item"))
                    //    item = props.get("item", String.class);
                }
                System.out.print(type + " ");
            }
            System.out.println();
        }
        System.out.println("width: " + width + " height: " + height);

    }
}
