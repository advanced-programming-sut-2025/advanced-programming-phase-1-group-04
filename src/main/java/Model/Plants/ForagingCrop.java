package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Map.Item;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Time.Weather;

import java.util.ArrayList;

public class ForagingCrop implements Item, Plant {
    private final ForagingCropType type;
    private final boolean purposelyPlanted;
    private final DateAndTime plantingDate;

    public ForagingCrop (ForagingCropType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
        plantingDate = new DateAndTime(9, 1, Weather.Sunny);
    }
    public ForagingCrop (DateAndTime t, ForagingCropType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
        plantingDate = new DateAndTime(t.getHour(), t.getDay(), Weather.Sunny);
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
        return "crop info:\nname: " + getName() + "\nis forageable: " + !purposelyPlanted;
    }

    public boolean isPurposelyPlanted() {
        return purposelyPlanted;
    }
}
