package Model.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    ArrayList<Farm> farms = new ArrayList<>();
    HashMap<Coordinate, Place> places = new HashMap<>();
    HashMap<Coordinate, Item> objects = new HashMap<>();
}
