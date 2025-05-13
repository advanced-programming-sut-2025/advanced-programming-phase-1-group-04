package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Map.Item;
import Model.Time.Season;

import java.util.ArrayList;

public class Sapling implements Item, Plant {
    private final SaplingType saplingType;

    public Sapling (SaplingType saplingType) {
        this.saplingType =saplingType;
    }

    @Override
    public String getName() {
        return saplingType.getName();
    }

    public ArrayList<Season> getSeasons() {
        return saplingType.getSeasons();
    }

    public TreeType getTreeType() {
        return saplingType.getTreeType();
    }

    public boolean isForageable() {
        return saplingType.isForageable();
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String showPlantInfo() {
        return "sapling info:\nname: " + getName() + "\ntree type: " + getTreeType();
    }
}
