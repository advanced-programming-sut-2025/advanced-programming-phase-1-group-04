package Model.Map;

import com.google.gson.Gson;

import java.io.*;

public class MakeRegionJson {
    public static void SaveJson(String fileName) throws IOException {
        Tile[][] tiles = MakeRegionJson.loadMapFromTextFile(fileName);
        Region region = new Region(fileName, tiles);

        Gson gson = new Gson();
        FileWriter writer = new FileWriter("resources/maps/" + fileName + ".json");
        gson.toJson(region, writer);
        writer.close();
        System.out.println("save shod. mobarak kheilia!");
    }

    public static Tile[][] loadMapFromTextFile(String filePath) throws IOException {
        Tile[][] tiles = new Tile[30][40];

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/maps/" + filePath + ".txt"))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 30) {
                for (int col = 0; col < Math.min(line.length(), 40); col++) {
                    String symbol = String.valueOf(line.charAt(col));
                    tiles[row][col] = MakeRegionJson.getTileFromString(symbol);
                }
                row++;
            }
        }

        return tiles;
    }

    private static Tile getTileFromString(String symbol) {
        switch (symbol) {
            case "M":
                return new Tile(TypeTile.Mountain);
            case "L":
                return new Tile(TypeTile.Water);
            case "X":
                return new Tile(TypeTile.Mine);

            case "G":
                return new Tile(TypeTile.Ground, false, null, null);
            case "T":

                return new Tile(TypeTile.Ground, false, null, null);
            case "^":

                return new Tile(TypeTile.Ground, false, null, null);
            case "*":
                return new Tile(TypeTile.Ground, true, null, null);

            case "H":
                return new Tile(TypeTile.Building, BuildingType.House);
            case "9":
                return new Tile(TypeTile.Building, BuildingType.GreenHouse);

            case "F":
                return new Tile(TypeTile.Building, BuildingType.FishShop);
            case "J":
                return new Tile(TypeTile.Building, BuildingType.JojaMart);
            case "B":
                return new Tile(TypeTile.Building, BuildingType.Blacksmith);
            case "I":
                return new Tile(TypeTile.Building, BuildingType.PierresGeneralStore);
            case "R":
                return new Tile(TypeTile.Building, BuildingType.MarniesRanch);
            case "C":
                return new Tile(TypeTile.Building, BuildingType.CarpentersShop);
            case "K":
                return new Tile(TypeTile.Building, BuildingType.TheStarDropSaloon);

            default:
                throw new IllegalArgumentException("Invalid tile character: " + symbol);
        }
    }

}
