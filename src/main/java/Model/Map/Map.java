package Model.Map;

import Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private Region[][] region = new Region[3][3];

    public Map(int player1, int player2, int player3, int player4) {
        region[0][0] = loadRegionJson("Farming" + player1);
        region[0][1] = loadRegionJson("Path1");
        region[0][2] = loadRegionJson("Farming" + player2);

        region[1][0] = loadRegionJson("Path4");
        region[1][1] = loadRegionJson("NPC");
        region[1][2] = loadRegionJson("Path2");

        region[2][0] = loadRegionJson("Farming" + player4);
        region[2][1] = loadRegionJson("Path3" + player1);
        region[2][2] = loadRegionJson("Farming" + player3);
    }

    private Region loadRegionJson(String name) {
        File file = new File("src/main/resources/Maps/" + name + ".json");

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Region.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
 }
