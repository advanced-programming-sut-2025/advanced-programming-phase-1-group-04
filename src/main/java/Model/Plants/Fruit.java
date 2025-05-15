package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Map.Item;

public class Fruit implements Item, Plant {
    private final FruitType type;
    private final boolean purposelyPlanted;

    //constructor:
    public Fruit (FruitType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
    }

    public Fruit (FruitType type) {
        this.type = type;
        this.purposelyPlanted = true;
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
        return "fruit info:\nname: " + getName() + "\nis forageable: " + !purposelyPlanted;
    }

    public boolean isPurposelyPlanted() {
        return purposelyPlanted;
    }
}