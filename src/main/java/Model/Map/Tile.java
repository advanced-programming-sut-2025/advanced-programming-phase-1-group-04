package Model.Map;

import Model.Animals.Animal;
import Model.NPC.NPC;
import Model.Player.Player;

public class Tile {
    private TypeTile type;
    private Coordinate coordinate;

    private boolean isDoor = false;
    private BuildingType buildingType = null;

    private boolean isPlowed = false;
    private boolean isWatered = false;
    private Item item = null;
    private Animal animal = null;
    private Player player = null;
    private NPC npc = null;
}

enum TypeTile {
    Building,
    Ground,
    Water
}
