package Model.Plants;

import Model.Enum.Season;

import java.util.ArrayList;

public class Sapling{
    private final SaplingType saplingType;

    public Sapling (SaplingType saplingType) {
        this.saplingType =saplingType;
    }

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
}