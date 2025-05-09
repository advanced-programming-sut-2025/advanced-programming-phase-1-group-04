package Model.Plants;

import Model.Time.Season;

import java.util.ArrayList;

public class ForagingCrop{
    private final ForagingCropType type;

    public ForagingCrop (ForagingCropType type) {
        this.type = type;
    }

    public String getName() {
        return type.getName();
    }

    public ArrayList<Season> getSeasons() {
        return type.getSeasons();
    }

    public int getBaseSellPrice() {
        return type.getBaseSellPrice();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public boolean isForageable () {
        return true;
    }
}
