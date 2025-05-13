package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Map.Item;

public class Fruit implements Item, Plant {
    private final FruitType type;

    //constructor:
    public Fruit (FruitType type) {
        this.type = type;
    }

    //getters:
    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        return type.getBaseSellPrice();
    }

    public boolean isEdible() {
        return type.isEdible();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public TreeType getTreeType() {
        return type.getTreeType();
    }

    @Override
    public String showPlantInfo() {
        return "fruit info:\nname: " + getName();
    }
}