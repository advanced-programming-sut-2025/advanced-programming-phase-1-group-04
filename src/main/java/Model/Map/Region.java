package Model.Map;

import Model.App;
import Model.Player.Player;

public class Region {
    private String name;
    private Tile[][] tiles;

    public Region(String name, Tile[][] tiles) {
        this.name = name;
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            for(int j = 0; j < 40; j++) {
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
                    result.append(tiles[i][j].getSymbol());
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
