package Model.Map;

public class Tile {
    private TypeTile type;

    Coordinate coordinate;

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}

enum TypeTile {
    Building,
    Ground,
    Water
}
