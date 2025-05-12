package Model.Plants;

import Model.App;
import Model.Map.Item;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Game;

import java.util.ArrayList;

public class Crop implements Item {
    private DateAndTime plantingDate;
    private DateAndTime lastTimeHarvested;
    private final CropType type;
    //TODO: isGiant?


    //constructor:
    private Crop (DateAndTime plantingDate, CropType type) {
        this.plantingDate = null;
        this.type = type;

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
        int daysSincePlanted = App.getCurrentGame().getCurrentTime().getDay() - plantingDate.getDay();
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
}
