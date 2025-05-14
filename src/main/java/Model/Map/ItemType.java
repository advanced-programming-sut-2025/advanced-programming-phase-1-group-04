package Model.Map;

public enum ItemType {
    Tree("tree"),
    Sapling("sapling"),
    Crop("crop"),
    Fruit("fruit"),
    Seed("seed"),
    ForagingCrop("foraging crop"),
    ForagingMineral("foraging mineral"),
    Stone("stone"),
    Wood("wood"),
    Craft("craft"),
    Food("food"),
    Fish("fish"),
    AnimalProduct("animal product"),
    ;

    private final String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
