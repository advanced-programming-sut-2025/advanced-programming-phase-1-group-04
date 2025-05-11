package Model.Plants;

import Model.Map.Item;
import Model.Time.Season;

import java.util.ArrayList;

public class ForagingCrop implements Item {
    private final ForagingCropType type;

    public ForagingCrop (ForagingCropType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    public ArrayList<Season> getSeasons() {
        return type.getSeasons();
    }

    @Override
    public int getPrice() {
        return type.getBaseSellPrice();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public boolean isForageable () {
        return true;
    }
}
