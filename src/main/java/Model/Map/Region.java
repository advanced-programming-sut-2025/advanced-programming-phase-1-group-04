package Model.Map;

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
                result.append(" ").append(tiles[i][j].getSymbol()).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
