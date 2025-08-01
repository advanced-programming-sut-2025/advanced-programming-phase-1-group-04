package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import io.Ap.StardewValley.Controller.GameScreenController;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.Screen.GameScreen;
import io.Ap.StardewValley.Screen.PlayerScreen.PlayerRender;
import io.Ap.StardewValley.StardewValley;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TiledMapRendererHelper {
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer renderer;

    public TiledMapRendererHelper(String mapName) {
        tiledMap = new TmxMapLoader().load("map/town/" + mapName + ".tmx");
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
            layer.setVisible(layer.getName().toLowerCase().contains("front"));


        renderer.setView(camera);
        renderer.render();
    }


    public void dispose() {
        tiledMap.dispose();
        renderer.dispose();
    }
}
