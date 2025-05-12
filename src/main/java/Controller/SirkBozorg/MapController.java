package Controller.SirkBozorg;

import Model.App;
import Model.Map.Symbols;
import Model.Map.Tile;
import Model.Result;

public class MapController {
    public static Result printAllMap() {
        return new Result(true, App.getCurrentGame().getMap().toString());
        //TODO: print player
    }

    public static Result printMap(String stringX, String stringY, String stringSize) {
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        int size = Integer.parseInt(stringSize);

        if ((x < 0 || x >= 90) || (y < 0 || y >= 120)) {
            return new Result(false, "Mashti x,y bein (0,0) - (89, 119)");
        } else if ((x + size < 0 || x + size >= 90) || (y + size < 0 || y + size >= 120)) {
            return new Result(false, "Size is invalid. Mashti x,y bein (0,0) - (89, 119)");
        }

        StringBuilder result = new StringBuilder();
        Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();
        for (int i = x; i < x + size; i++){
            for(int j = y; j < y +size; j++) {
                result.append(" ").append(fullMap[i][j].getSymbol()).append(" ");
            }
            result.append("\n");
        }
        //TODO: print players
        return new Result(true, result.toString());
    }

    public static Result helpMap(){
        StringBuilder result = new StringBuilder();
        for (Symbols s: Symbols.values()) {
            result.append(s.name()).append(s.getColoredSymbol()).append("\n");
        }

        return new Result(true, result.toString());
    }



}
