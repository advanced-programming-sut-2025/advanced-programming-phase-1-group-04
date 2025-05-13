package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Map.Item;
import Model.Time.Season;

import java.util.ArrayList;

public class ForagingCrop implements Item, Plant {
    private final ForagingCropType type;
    private final boolean purposelyPlanted;

    public ForagingCrop (ForagingCropType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
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

    @Override
    public String showPlantInfo() {
        return "foraging crop info:\nname: " + getName();
    }

    public boolean isPurposelyPlanted() {
        return purposelyPlanted;
    }
}
