package Model.Map;

import Model.Animals.Animal;
import Model.NPC.NPC;


public class Tile {
    private TileType type;

    //private boolean isDoor = false; TODO: baraye in faz dar hesab nakardam
    private BuildingType buildingType = null;

    private boolean isPavement = false;
    private Item item = null;
    private NPC npc = null; // TODO: npc nadarim felan dosrost shod add mikonam

    private boolean isPlowed = false;
    private boolean isWatered = false;
    private Animal animal = null;

    public Tile(TileType type) { // For Water, Mountain, Mine
        this.type = type;
    }

    public Tile(TileType type, BuildingType buildingType) { // For Building
        this.type = type;
        this.buildingType = buildingType;
    }

    public Tile(TileType type, boolean isPavement, Item item, NPC npc){ // For Ground
        this.type = type;
        this.isPavement = isPavement;
        this.item = item;
        this.npc = npc;
    }

    public boolean isWalkable() {

        return false;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }
}
