package Model.Map;

import Model.Plants.ForagingCrop;
import Model.Plants.ForagingCropType;
import Model.Plants.Tree;
import Model.Plants.TreeType;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Time.Weather;
import Model.Time.WeekDay;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Gson.ItemAdapter;

import java.io.*;

public class MakeRegionJson {
    public static void SaveJson(String fileName) throws IOException {
        Tile[][] tiles = MakeRegionJson.loadMapFromTextFile(fileName);
        Region region = new Region(fileName, tiles);

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Item.class, new ItemAdapter())
            .create();

        FileWriter writer = new FileWriter("src/main/resources/Maps/" + fileName + ".json");
        gson.toJson(region, writer);
        writer.close();
        System.out.println("save shod. mobarak kheilia!");
    }

    private static Tile[][] loadMapFromTextFile(String filePath) throws IOException {
        Tile[][] tiles = new Tile[30][40];

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Maps/" + filePath + ".txt"))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < 30) {
                String[] symbols = line.trim().split("\\s+");

                for (int col = 0; col < Math.min(symbols.length, 40); col++) {
                    char symbol = symbols[col].charAt(0);
                    tiles[row][col] = MakeRegionJson.getTileFromString(symbol);
                }
                row++;
            }
        }

        return tiles;
    }

    private static Tile getTileFromString(char symbol) {
        switch (symbol) {
            case 'M':
                return new Tile(TileType.Mountain);
            case 'L':
                return new Tile(TileType.Water);
            case 'X':
                return new Tile(TileType.Mine);

            case 'G':
                return new Tile(TileType.Ground, false, null, null);
            case 'T':
                Tree tree = new Tree(TreeType.Apricot, new DateAndTime(9, 1, Weather.Sunny), false);
                return new Tile(TileType.Ground, false, tree, null);
            case '^':
                ForagingCrop foraging = new ForagingCrop(ForagingCropType.CommonMushroom, false);
                return new Tile(TileType.Ground, false, foraging, null);
            case '*':
                return new Tile(TileType.Ground, true, null, null);

            case 'H':
                return new Tile(TileType.Building, BuildingType.House);
            case '9':
                return new Tile(TileType.Building, BuildingType.GreenHouse);

            case 'F':
                return new Tile(TileType.Building, BuildingType.FishShop);
            case 'J':
                return new Tile(TileType.Building, BuildingType.JojaMart);
            case 'B':
                return new Tile(TileType.Building, BuildingType.Blacksmith);
            case 'I':
                return new Tile(TileType.Building, BuildingType.PierresGeneralStore);
            case 'R':
                return new Tile(TileType.Building, BuildingType.MarniesRanch);
            case 'C':
                return new Tile(TileType.Building, BuildingType.CarpentersShop);
            case 'K':
                return new Tile(TileType.Building, BuildingType.TheStarDropSaloon);
            case '0':
                return new Tile(TileType.Building, BuildingType.ShippingBin);

            default:
                System.out.println("Unknown symbol: '" + symbol + "'");
                throw new IllegalArgumentException("Invalid tile character: " + symbol);
        }
    }

}
