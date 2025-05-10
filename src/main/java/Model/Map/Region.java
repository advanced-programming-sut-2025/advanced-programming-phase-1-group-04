package Model.Map;

public class Region {
    private String name;
    private Tile[][] tiles;

    public Region(String name, Tile[][] tiles) {
        this.name = name;
        this.tiles = tiles;
    }
}
