package io.Ap.StardewValley.Model.Map;

import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import io.Ap.StardewValley.Gson.ItemAdapter;

public class GameMap {
    private final Region[][] region = new Region[3][3];
    private transient Tile[][] fullMap;

    public GameMap(int[] farmSelection) {
        region[0][0] = loadRegionJson("Farming"  + farmSelection[0]);
        //region[0][0] = loadRegionJson("Farm"  + farmSelection[0]);
        region[0][1] = loadRegionJson("Path1");
        region[0][2] = loadRegionJson("Farming" + farmSelection[1]);

        region[1][0] = loadRegionJson("Path4");
        region[1][1] = loadRegionJson("NPC");
        region[1][2] = loadRegionJson("Path2");

        region[2][0] = loadRegionJson("Farming" + farmSelection[3]);
        region[2][1] = loadRegionJson("Path3");
        region[2][2] = loadRegionJson("Farming" + farmSelection[2]);

        /*mine[0] = loadRegionJson("Mine" + farmSelection[0]);
        mine[1] = loadRegionJson("Mine" + farmSelection[1]);
        mine[2] = loadRegionJson("Mine" + farmSelection[2]);
        mine[3] = loadRegionJson("Mine" + farmSelection[3]);*/

    }

    private Region loadRegionJson(String name) {
        File file = new File("core/src/main/resources/Maps/" + name + ".json"); // TODO: این با فایل جر کار نمیکنه

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemAdapter()) // ثبت TypeAdapter
                .create();
            return gson.fromJson(reader, Region.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setFulMap() {
        // Phase 1:
        // total rows = 3 * 30, total cols = 3 * 40;


        // calculate total width, height:
        int rows = region.length;
        int cols = region[0].length;

        int totalRows = 0;
        int totalCols = 0;

        int[] rowHeights = new int[rows];
        int[] colWidths = new int[cols];

        for (int i = 0; i < rows; i++) {
            int maxHeight = 0;
            for (int j = 0; j < cols; j++) {
                Region r = region[i][j];
                if (r != null) {
                    maxHeight = Math.max(maxHeight, r.getTiles().length);
                }
            }
            rowHeights[i] = maxHeight;
            totalRows += maxHeight;
        }

        for (int j = 0; j < cols; j++) {
            int maxWidth = 0;
            for (int i = 0; i < rows; i++) {
                Region r = region[i][j];
                if (r != null) {
                    maxWidth = Math.max(maxWidth, r.getTiles()[0].length);
                }
            }
            colWidths[j] = maxWidth;
            totalCols += maxWidth;
        }

        // Build Full map:
        fullMap = new Tile[totalRows][totalCols];

        int rowOffset = 0;
        for (int i = 0; i < rows; i++) {
            int colOffset = 0;
            for (int j = 0; j < cols; j++) {
                Region r = region[i][j];
                if (r == null) continue;

                Tile[][] tiles = r.getTiles();
                int h = tiles.length;
                int w = tiles[0].length;

                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        fullMap[rowOffset + y][colOffset + x] = tiles[y][x];
                    }
                }

                colOffset += colWidths[j];
            }
            rowOffset += rowHeights[i];
        }
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        int height = fullMap.length;
        int width = fullMap[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.append(" ");
                Coordinate coordinate = new Coordinate(i, j);
                boolean isPlayer = false;
                for (Player player: App.getGame().getPlayers()) {
                    if (player.getCoordinate().equals(coordinate)) {
                        isPlayer = true;
                        if (player.getId() == App.getGame().getCurrentPlayer().getId())
                            result.append(Symbols.CurrentPlayer.getColoredSymbol());
                        else
                            result.append(Symbols.Player.getColoredSymbol());
                    }
                }
                if (!isPlayer)
                    result.append(fullMap[i][j].getSymbol());
                result.append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public Tile[][] getFullMap() {
        return fullMap;
    }

    public Region getRegion(int i, int j) {
        return region[i][j];
    }

    public void build(int x, int y, BuildingType type) {
        for (int i = x; i < type.getW() + x; i++) {
            for (int j = y; j < type.getL() + y; j++) {
                fullMap[i][j].setType(TileType.Building);
                fullMap[i][j].setBuildingType(type);
            }
        }
    }
}
