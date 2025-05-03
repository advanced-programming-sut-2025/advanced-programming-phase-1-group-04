package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class Sapling implements Forageable{
    private final SaplingType saplingType;

    public Sapling (SaplingType saplingType) {
        this.saplingType =saplingType;
    }

    public String getName() {
        return saplingType.getName();
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return saplingType.getSeasons();
    }

    public TreeType getTreeType() {
        return saplingType.getTreeType();
    }

    @Override
    public boolean isForageable() {
        return saplingType.isForageable();
    }
}
