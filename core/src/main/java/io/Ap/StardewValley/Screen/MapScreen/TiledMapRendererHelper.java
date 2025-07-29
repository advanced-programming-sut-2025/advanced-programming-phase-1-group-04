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

    public TiledMapRendererHelper(String pathToTmx) {
        tiledMap = new TmxMapLoader().load(pathToTmx);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        //printTiledLayerInformation();
        saveTiledMapInfo(extractLayerTypesUpToFront(), "core/src/main/resources/TiledMaps/Farm.txt");
    }

    public SpriteBatch getBatch() {
        return (SpriteBatch) renderer.getBatch();
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

    private void printTiledLayerInfo() {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("Back");

        int width = layer.getWidth();
        int height = layer.getHeight();
        Tile[][] tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                String type = "void";

                if (cell != null) {
                    TiledMapTile tile = cell.getTile();
                    MapProperties props = tile.getProperties();

                    if (props.containsKey("Type"))
                        type = props.get("Type", String.class);
                }
                System.out.print(type + " ");
            }
            System.out.println();
        }
        System.out.println("width: " + width + " height: " + height);

    }

    private String[][] extractLayerTypesUpToFront() {
        int mapWidth = 0;
        int mapHeight = 0;

        for (MapLayer layer : tiledMap.getLayers()) {
            if (layer instanceof TiledMapTileLayer) {
                TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;
                mapWidth = tileLayer.getWidth();
                mapHeight = tileLayer.getHeight();
                break;
            }
        }

        String[][] result = new String[mapWidth][mapHeight];

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                result[x][y] = "void";
            }
        }

        for (MapLayer layer : tiledMap.getLayers()) {
            if (!(layer instanceof TiledMapTileLayer)) continue;
            if (layer.getName().toLowerCase().contains("front")) break;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;

            for (int x = 0; x < tileLayer.getWidth(); x++) {
                for (int y = 0; y < tileLayer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                    if (cell != null) {
                        TiledMapTile tile = cell.getTile();
                        if (tile != null) {
                            MapProperties props = tile.getProperties();
                            if (props.containsKey("Type")) {
                                result[x][y] = props.get("Type", String.class);
                            }
                        }
                    }
                }
            }
        }

//        for (int y = mapHeight - 1; y >= 0; y--) {
//            for (int x = 0; x < mapWidth; x++) {
//                System.out.print(result[x][y] + " ");
//            }
//            System.out.println();
//        }

        return result;
    }

    public void saveTiledMapInfo(String[][] data, String filePath) {
        int height = data[0].length;
        int width = data.length;

        // مرحله ۱: پیدا کردن بیشترین طول کلمه
        int maxLength = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (data[x][y] != null) {
                    maxLength = Math.max(maxLength, data[x][y].length());
                }
            }
        }

        // مرحله ۲: نوشتن خروجی با فاصله تنظیم‌شده
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    String value = data[x][y] != null ? data[x][y] : "";
                    writer.write(padRight(value, maxLength + 2));  // فاصله اضافه برای خوانایی بهتر
                }
                writer.newLine();
            }

            System.out.println("File saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write file: " + e.getMessage());
        }
    }

    private String padRight(String text, int length) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }

}
