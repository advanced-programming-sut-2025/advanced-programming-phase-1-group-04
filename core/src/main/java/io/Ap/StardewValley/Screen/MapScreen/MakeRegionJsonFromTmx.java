package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.Ap.StardewValley.Gson.ItemAdapter;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Map.*;
import io.Ap.StardewValley.Model.Plants.ForagingCrop;
import io.Ap.StardewValley.Model.Plants.ForagingCropType;
import io.Ap.StardewValley.Model.Plants.Tree;
import io.Ap.StardewValley.Model.Plants.TreeType;
import io.Ap.StardewValley.Model.Time.DateAndTime;
import io.Ap.StardewValley.Model.Time.Weather;

import java.io.*;

public class MakeRegionJsonFromTmx {
    public static void SaveJson(TiledMap tiledMap, String fileName) throws IOException {
        Tile[][] tiles = MakeRegionJsonFromTmx.loadTilesFromTiledMap(tiledMap);
        Region region = new Region(fileName, tiles);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemAdapter())
                .create();

        FileWriter writer = new FileWriter("core/src/main/resources/TiledMaps/" + fileName + ".json");
        gson.toJson(region, writer);
        writer.close();
        System.out.println("save shod. mobarak kheilia!");
    }

    private static Tile[][] loadTilesFromTiledMap(TiledMap tiledMap) throws IOException {
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

        Tile[][] result = new Tile[mapWidth][mapHeight];

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                result[x][y] = null;
            }
        }

        for (MapLayer layer : tiledMap.getLayers()) {
            if (!(layer instanceof TiledMapTileLayer)) continue;
            if (layer.getName().toLowerCase().contains("front")) break;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) layer;

            for (int y = tileLayer.getHeight() - 1; y >= 0; y--) {
                for (int x = 0; x < tileLayer.getWidth(); x++) {
                    TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                    if (cell != null) {
                        TiledMapTile tile = cell.getTile();
                        if (tile != null) {
                            if (getTile(tile) != null)
                                result[x][y] = getTile(tile); // حذف mapHeight - 1 - y
                        }
                    }
                }
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (result[x][y] == null) {
                    throw new IOException("Tile at (" + x + "," + y + ") is null.");
                }
            }
        }

        return result;
    }

    private static Tile getTile(TiledMapTile tile) {
        // Building: ShippingBin, MarinesRanch, Door, Building(DontKnow), WizardBuilding
        //          PierresGeneralStore, CarpentersShop, Saloon, FishShop, JojaMart, BlackSmith
        MapProperties props = tile.getProperties();
        if (props.containsKey("Type")) {
            switch (props.get("Type", String.class)) {
                case "Mountain":
                    return new Tile(TileType.Mountain);
                case "Water":
                    return new Tile(TileType.Water);
                case "Mine":
                    return new Tile(TileType.Mine);

                case "Ground":
                    return new Tile(TileType.Ground, false, null, null);
                case "Wood", "Rail", "Stone":
                    return new Tile(TileType.Ground, true, null, null);

                case "H":
                    return new Tile(TileType.Building, BuildingType.House);
                case "9":
                    return new Tile(TileType.Building, BuildingType.GreenHouse);
                case "ShippingBin":
                    return new Tile(TileType.Building, BuildingType.ShippingBin);

                case "FishShop":
                    return new Tile(TileType.Building, BuildingType.FishShop);
                case "JojaMart":
                    return new Tile(TileType.Building, BuildingType.JojaMart);
                case "BlackSmith":
                    return new Tile(TileType.Building, BuildingType.Blacksmith);
                case "PierresGeneralStore":
                    return new Tile(TileType.Building, BuildingType.PierresGeneralStore);
                case "MarinesRanch":
                    return new Tile(TileType.Building, BuildingType.MarniesRanch);
                case "CarpentersShop":
                    return new Tile(TileType.Building, BuildingType.CarpentersShop);
                case "Saloon":
                    return new Tile(TileType.Building, BuildingType.TheStarDropSaloon);
                case "Door":
                    return new Tile(TileType.Building, BuildingType.Door);
                case "Building", "WizardBuilding":
                    return new Tile(TileType.Building, BuildingType.DontKnow);

                default:
                    return null;
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------

    public static void printLayerInfo(TiledMap tiledMap, String nameLayer) {
        TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(nameLayer);

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

    public static void saveTxt(TiledMap tiledMap,  String fileName) {
        String[][] data = getTypes(tiledMap);
        int height = data[0].length;
        int width = data.length;

        int maxLength = 0;
        for (String[] datum : data) {
            for (int y = 0; y < height; y++) {
                if (datum[y] != null) {
                    maxLength = Math.max(maxLength, datum[y].length());
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("core/src/main/resources/TiledMaps/" + fileName + ".txt"))) {
            for (int y = height - 1; y >= 0; y--) {
                for (int x = 0; x < width; x++) {
                    writer.write(padRight(data[x][y], maxLength + 2));
                }
                writer.newLine();
            }
            writer.newLine();
            writer.write("Width: " + width + " Height: " + height + " MaxLength: " + maxLength);

            System.out.println("Save shod mobarake!");
        } catch (IOException e) {
            System.err.println("Failed to write file: " + e.getMessage());
        }
    }

    private static String[][] getTypes(TiledMap tiledMap) {
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
                            if (getTile(tile) == null) {
                                if (result[x][y].equals("void")) {
                                    result[x][y] = "shash(" + layer.getName() + ")";
                                }
                            } else {
                                result[x][y] = tile.getProperties().get("Type", String.class);
                            }
                        }
                    }
                }
            }
        }

        // HELP TXT IN CONSOLE:
//        for (int y = mapHeight - 1; y >= 0; y--) {
//            for (int x = 0; x < mapWidth; x++) {
//                //System.out.print(result[x][y] + " ");
//                if (x == 1 && y == 62) {
//                    System.out.println(result[x][y]);
//                }
//            }
//            //System.out.println();
//        }

        return result;
    }

    private static String padRight(String text, int length) {
        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
