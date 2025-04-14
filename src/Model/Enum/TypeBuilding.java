package Model.Enum;

import Model.Map.Obj;

import java.util.Map;

public enum TypeBuilding {
    Greenhouse (6, 5, Map.of(Obj(TypeObj.Coin), 1000, new Obj(TypeObj.Wood), 500)),
    Cottage ();

    private final int length;
    private final int width;
    private final Map<Obj, Integer> ingredients;

    TypeBuilding(int length, int width, Map<Obj, Integer> ingredients) {
        this.length = length;
        this.width = width;
        this.ingredients = ingredients;
    }
}
