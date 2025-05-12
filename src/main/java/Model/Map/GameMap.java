package Model.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Gson.ItemAdapter;

public class GameMap {
    private Region[][] region = new Region[3][3];
    private Tile[][] fullMap;
    public GameMap(int[] farmSelection) {
        region[0][0] = loadRegionJson("Farming"  + farmSelection[0]);
        region[0][1] = loadRegionJson("Path1");
        region[0][2] = loadRegionJson("Farming" + farmSelection[1]);

        region[1][0] = loadRegionJson("Path4");
        region[1][1] = loadRegionJson("NPC");
        region[1][2] = loadRegionJson("Path2");

        region[2][0] = loadRegionJson("Farming" + farmSelection[3]);
        region[2][1] = loadRegionJson("Path3");
        region[2][2] = loadRegionJson("Farming" + farmSelection[2]);
    }

    private Region loadRegionJson(String name) {
        File file = new File("src/main/resources/Maps/" + name + ".json");

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
        int tileRowsPerRegion = 30;
        int tileColsPerRegion = 40;
        int totalRows = 3 * 30;
        int totalCols = 3 * 40;

        Tile[][] fullMap = new Tile[totalRows][totalCols];

        for (int regionRow = 0; regionRow < 3; regionRow++) {
            for (int regionCol = 0; regionCol < 3; regionCol++) {
                Region region = this.region[regionRow][regionCol];

                Tile[][] tiles = region.getTiles();
                for (int i = 0; i < tileRowsPerRegion; i++) {
                    for (int j = 0; j < tileColsPerRegion; j++) {
                        int fullRow = regionRow * tileRowsPerRegion + i;
                        int fullCol = regionCol * tileColsPerRegion + j;
                        fullMap[fullRow][fullCol] = tiles[i][j];
                    }
                }
            }
        }

        this.fullMap = fullMap;
    }

    @Override
    public String toString() {
        int totalRows = 3 * 30;
        int totalCols = 3 * 40;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                result.append(" ").
                    append(fullMap[i][j].getSymbol()).
                    append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public Tile[][] getFullMap() {
        return fullMap;
    }
}
