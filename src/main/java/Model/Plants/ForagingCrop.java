package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.App;
import Model.Map.Item;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Time.Weather;

import java.util.ArrayList;

public class ForagingCrop implements Item, Plant {
    private final ForagingCropType type;
    private final boolean purposelyPlanted;
    private final DateAndTime plantingDate;
    private double qualityConst = 1;

    public ForagingCrop (ForagingCropType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
        plantingDate = new DateAndTime(App.getCurrentGame().getCurrentTime().getHour(),
                App.getCurrentGame().getCurrentTime().getDay(), App.getCurrentGame().getCurrentTime().getWeather());
    }

    public ForagingCrop (DateAndTime t, ForagingCropType type, boolean purposelyPlanted) {
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
        plantingDate = new DateAndTime(t.getHour(), t.getDay(), t.getWeather());
    }

    public ForagingCrop (ForagingCropType type) {
        this.type = type;
        this.purposelyPlanted = false;
        plantingDate = new DateAndTime(App.getCurrentGame().getCurrentTime().getHour(),
                App.getCurrentGame().getCurrentTime().getDay(), App.getCurrentGame().getCurrentTime().getWeather());
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
        return (int) (qualityConst * type.getBaseSellPrice());
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

    public double getQualityConst() {
        return qualityConst;
    }

    public void setQualityConst(double qualityConst) {
        this.qualityConst = qualityConst;
    }

    public DateAndTime getPlantingDate() {
        return plantingDate;
    }

    public ForagingCropType getType() {
        return type;
    }
}
