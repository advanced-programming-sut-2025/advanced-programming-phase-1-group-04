package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class ForagingCrop implements Forageable{
    private final ForagingCropType type;

    public ForagingCrop (ForagingCropType type) {
        this.type = type;
    }

    public String getName() {
        return type.getName();
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return type.getSeasons();
    }

    public int getBaseSellPrice() {
        return type.getBaseSellPrice();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    @Override
    public boolean isForageable () {
        return true;
    }
}