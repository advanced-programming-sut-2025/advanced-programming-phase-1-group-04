package Model.Map;

import Model.App;
import Model.Player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Gson.ItemAdapter;

public class GameMap {
    private Region[][] region = new Region[3][3];
    private transient Tile[][] fullMap;

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

        /*mine[0] = loadRegionJson("Mine" + farmSelection[0]);
        mine[1] = loadRegionJson("Mine" + farmSelection[1]);
        mine[2] = loadRegionJson("Mine" + farmSelection[2]);
        mine[3] = loadRegionJson("Mine" + farmSelection[3]);*/

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
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3 * 30; i++) {
            for (int j = 0; j < 3 * 40; j++) {
                result.append(" ");
                Coordinate coordinate = new Coordinate(i, j);
                boolean isPlayer = false;
                for (Player player: App.getCurrentGame().getPlayers()) {
                    if (player.getCoordinate().equals(coordinate)) {
                        isPlayer = true;
                        if (player.getId() == App.getCurrentGame().getCurrentPlayer().getId())
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
