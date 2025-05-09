package Model.Animals;

public enum AnimalType {
    Chicken("Well cared-for chickens lay eggs every day. Lives in the coop." , "Coop"),
    Cow("Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn." , "Barn"),
    Goat("Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn." , "BigBarn"),
    Duck("Happy lay duck eggs every other day. Lives in the coop." , "BigCoop"),
    Sheep("Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn." , "DeluxeBarn"),
    Rabbit("These are wooly rabbits! They shed precious wool every few days. Lives in the coop." , "DeluxeCoop"),
    Dinosaur("The Dinosaur is a farm animal that lives in a Big Coop" , "BigCoop"),
    Pig("These pigs are trained to find truffles! Lives in the barn." , "DeluxeBarn");

    private final String description;
    private final String buildingRequired;

    AnimalType(String description, String buildingRequired) {
        this.description = description;
        this.buildingRequired = buildingRequired;
    }

    public String getDescription() {
        return description;
    }

    public String getBuildingRequired() {
        return buildingRequired;
    }

    public String getName() {
        return this.toString();
    }
}
