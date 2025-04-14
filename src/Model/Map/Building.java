package Model.Map;

import Model.Enum.TypeBuilding;

public class Building {
    private TypeBuilding type;
    private Coordinate coordinate;

    public Building(TypeBuilding type, Coordinate coordinate) {
        this.type = type;
        this.coordinate = coordinate;
    }
}
