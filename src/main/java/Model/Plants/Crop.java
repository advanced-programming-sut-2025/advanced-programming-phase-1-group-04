package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.App;
import Model.Map.Item;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Game;
import Model.Time.Weather;

import java.util.ArrayList;

public class Crop implements Item, Plant {
    private DateAndTime plantingDate;
    private DateAndTime lastTimeHarvested;
    private final CropType type;
    private final boolean purposelyPlanted;
    //TODO: isGiant?


    //constructor:
    public Crop(DateAndTime t, CropType type, boolean purposelyPlanted) {
        this.plantingDate = new DateAndTime(t.getHour(), t.getDay(), t.getWeather());
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
    }
    public Crop(CropType type, boolean purposelyPlanted) {
        this.plantingDate = new DateAndTime(App.getCurrentGame().getCurrentTime().getHour(),
                App.getCurrentGame().getCurrentTime().getDay(), App.getCurrentGame().getCurrentTime().getWeather());
        this.type = type;
        this.purposelyPlanted = purposelyPlanted;
    }


    //getters:
    @Override
    public String getName() {
        return type.getName();
    }

    public SeedType getSource() {
        return type.getSource();
    }

    public int[] getStages() {
        return type.getStages();
    }

    public int getHarvestTime() {
        return type.getHarvestTime();
    }

    public boolean isOneTime() {
        return type.isOneTime();
    }

    public int getRegrowthTime() {
        return type.getRegrowthTime();
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

    public ArrayList<Season> getSeasons() {
        return type.getSeasons();
    }

    public boolean canBecomeGiant() {
        return type.canBecomeGiant();
    }

    public boolean isMixed() {
        return type.isMixed();
    }

    public DateAndTime getPlantingDate() {
        return plantingDate;
    }

    public DateAndTime getLastTimeHarvested() {
        return lastTimeHarvested;
    }

    public int getCurrentStage () {
        int stageTime = getHarvestTime();
        int currentStage = getStages().length + 1;
        int daysSincePlanted = App.getCurrentGame().getCurrentTime().getDay() - plantingDate.getDay();//TODO: in moshkel dare
        if (lastTimeHarvested == null) {
            if (daysSincePlanted >= stageTime) {
                return currentStage;
            }
            for (int i : getStages()) {
                currentStage--;
                stageTime -= i;
                if (daysSincePlanted >= stageTime) {
                    return currentStage;
                }
            }
        }
        else {
            if (App.getCurrentGame().getCurrentTime().getDay() - lastTimeHarvested.getDay() >= getRegrowthTime()) {
                return getStages().length;
            }
            else {
                return 1;
            }
        }
        return 1;
    }


    //setters:
    public void setPlantingDate(DateAndTime plantingDate) {
        this.plantingDate = plantingDate;
    } //TODO: in bashe ya na?

    public void setLastTimeHarvested(DateAndTime lastTimeHarvested) {
        this.lastTimeHarvested = lastTimeHarvested;
    }


    @Override
    public String showPlantInfo() {
        return "crop info:\nname: " + getName() + "\ntotal harvest time: " + getHarvestTime() + "\ndate of planting: " +
                plantingDate.getSeason().name() + " " + plantingDate.getDay() + "th\nstages: " +
                PlantController.stagesToString(getStages()) + "\ncurrent stage: " + getCurrentStage() + "\nis foraging: " +
                !purposelyPlanted;
    }

    public boolean isPurposelyPlanted() {
        return purposelyPlanted;
    }
}
