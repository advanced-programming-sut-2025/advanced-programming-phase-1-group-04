package Model.Map;

import Model.Animals.Animal;
import Model.NPC.NPC;
import Model.Player.Player;

public class Tile {
    private TypeTile type;

    private boolean isDoor = false;
    private BuildingType buildingType = null;

    private boolean isPlowed = false;
    private boolean isWatered = false;
    private boolean isPavement = false;
    private Item item = null;
    private Animal animal = null;
    private Player player = null;
    private NPC npc = null;

    public Tile(TypeTile type, boolean isDoor, BuildingType buildingType) {
        this.type = type;
        this.isDoor = isDoor;
        this.buildingType = buildingType;
    }

    public TypeTile getType() {
        return type;
    }

    public void setType(TypeTile type) {
        this.type = type;
    }

    public boolean isDoor() {
        return isDoor;
    }

    public void setDoor(boolean door) {
        isDoor = door;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }
}

enum TypeTile {
    Building,
    Ground,
    Water,
    Mountain
}
