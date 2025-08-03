package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import io.Ap.StardewValley.Model.App;

import java.io.IOException;

public class TiledMapRendererHelper {
    private final float EpsilonYSorting = 8f;

    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer renderer;

    public TiledMapRendererHelper(String mapName) {
        tiledMap = new TmxMapLoader().load("map/maps/" + mapName + ".tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

//        MakeRegionJsonFromTmx.saveTxt(tiledMap, mapName);
//
//        try {
//            MakeRegionJsonFromTmx.SaveJson(tiledMap, mapName);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void renderAllLayers(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void renderOneLayer(OrthographicCamera camera, String layerName) {
        MapLayers layers = tiledMap.getLayers();

        for (MapLayer layer : layers) {
            layer.setVisible(false);
        }
        MapLayer targetLayer = layers.get(layerName);
        if (targetLayer != null) {
            targetLayer.setVisible(true);
        }

        renderer.setView(camera);
        renderer.render();
    }


    public void renderBeforePlayer(OrthographicCamera camera) {
        MapLayers layers = tiledMap.getLayers();

        for (MapLayer layer : layers)
            layer.setVisible(!layer.getName().toLowerCase().contains("front"));

        renderer.setView(camera);
        renderer.render();
    }

    public void renderAfterPlayer(OrthographicCamera camera) {
        MapLayers layers = tiledMap.getLayers();

        for (MapLayer layer : layers)
            layer.setVisible(layer.getName().toLowerCase().contains("always"));


        renderer.setView(camera);
        renderer.render();
    }

    // Y-sorting: (for front Layer)
    public void renderDynamicAboveLayer(OrthographicCamera camera) {
        float playerY = App.getGame().getCurrentPlayer().getYLibGdx();
        TiledMapTileLayer dynamicLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Front");

        renderer.setView(camera);
        SpriteBatch batch = (SpriteBatch) renderer.getBatch();

        batch.begin();
        for (int x = 0; x < dynamicLayer.getWidth(); x++) {
            for (int y = 0; y < dynamicLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = dynamicLayer.getCell(x, y);
                if (cell == null || cell.getTile() == null) continue;

                float tileY = y * dynamicLayer.getTileHeight();
                if (tileY <= playerY + EpsilonYSorting) {
                    batch.draw(
                            cell.getTile().getTextureRegion(),
                            x * dynamicLayer.getTileWidth(),
                            y * dynamicLayer.getTileHeight()
                    );
                }
            }
        }
        batch.end();
    }

    public void renderDynamicBelowLayer(OrthographicCamera camera) {
        float playerY = App.getGame().getCurrentPlayer().getYLibGdx();
        TiledMapTileLayer dynamicLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Front");

        renderer.setView(camera);
        SpriteBatch batch = (SpriteBatch) renderer.getBatch();

        batch.begin();
        for (int x = 0; x < dynamicLayer.getWidth(); x++) {
            for (int y = 0; y < dynamicLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = dynamicLayer.getCell(x, y);
                if (cell == null || cell.getTile() == null) continue;

                float tileY = y * dynamicLayer.getTileHeight();
                if (tileY >= playerY + EpsilonYSorting) {
                    batch.draw(
                            cell.getTile().getTextureRegion(),
                            x * dynamicLayer.getTileWidth(),
                            y * dynamicLayer.getTileHeight()
                    );
                }
            }
        }
        batch.end();
    }



    public int getWidthPixels() {
        MapProperties props = tiledMap.getProperties();
        int widthInTiles = props.get("width", Integer.class);
        int tileWidth = props.get("tilewidth", Integer.class);
        return widthInTiles * tileWidth;
    }

    public int getHeightPixels() {
        MapProperties props = tiledMap.getProperties();
        int heightInTiles = props.get("height", Integer.class);
        int tileHeight = props.get("tileheight", Integer.class);
        return heightInTiles * tileHeight;
    }



    public void dispose() {
        tiledMap.dispose();
        renderer.dispose();
    }
}
