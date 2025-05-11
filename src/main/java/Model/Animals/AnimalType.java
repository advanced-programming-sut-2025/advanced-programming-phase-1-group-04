package Model.Animals;

import java.util.ArrayList;
import java.util.List;

public enum AnimalType {
    Chicken("Well cared-for chickens lay eggs every day. Lives in the coop." , "Coop", new ArrayList<>(List.of(AnimalProductType.Egg, AnimalProductType.BigEgg))),
    Duck("Happy lay duck eggs every other day. Lives in the coop." , "BigCoop", new ArrayList<>(List.of(AnimalProductType.DuckEgg, AnimalProductType.DuckFeather))),
    Rabbit("These are wooly rabbits! They shed precious wool every few days. Lives in the coop." , "DeluxeCoop", new ArrayList<>(List.of(AnimalProductType.RabbitFur, AnimalProductType.RabbitFoot))),
    Dinosaur("The Dinosaur is a farm animal that lives in a Big Coop" , "BigCoop", new ArrayList<>(List.of(AnimalProductType.DinosaurEgg))),
    Cow("Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn." , "Barn", new ArrayList<>(List.of(AnimalProductType.CowMilk, AnimalProductType.BigCowMilk))),
    Goat("Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn." , "BigBarn", new ArrayList<>(List.of(AnimalProductType.GoatMilk, AnimalProductType.BigGoatMilk))),
    Sheep("Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn." , "DeluxeBarn", new ArrayList<>(List.of(AnimalProductType.SheepWool))),
    Pig("These pigs are trained to find truffles! Lives in the barn." , "DeluxeBarn", new ArrayList<>(List.of(AnimalProductType.Truffle)));

    private final String description;
    private final String buildingRequired;
    private final ArrayList<AnimalProductType> products;

    AnimalType(String description, String buildingRequired, ArrayList<AnimalProductType> products) {
        this.description = description;
        this.buildingRequired = buildingRequired;
        this.products = products;
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
