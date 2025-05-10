package Model.Map;

import Model.Animals.Animal;
import Model.NPC.NPC;
import Model.Player.Player;

public class Tile {
    private TypeTile type;

    //private boolean isDoor = false; // TODO: baraye in faz dar hesab nakardam
    private BuildingType buildingType = null;

    private boolean isPavement = false;
    private Item item = null;
    private NPC npc = null; // TODO: npc nadarim felan dosrost shod add mikonam

    private boolean isPlowed = false;
    private boolean isWatered = false;
    private Animal animal = null;

    public Tile(TypeTile type) { // For Water, Mountain, Mine
        this.type = type;
    }

    public Tile(TypeTile type, BuildingType buildingType) { // For Building
        this.type = type;
        this.buildingType = buildingType;
    }

    public Tile(TypeTile type, boolean isPavement, Item item, NPC npc){ // For Ground
        this.type = type;
        this.isPavement = isPavement;
        this.item = item;
        this.npc = npc;
    }

    public TypeTile getType() {
        return type;
    }

    public void setType(TypeTile type) {
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
