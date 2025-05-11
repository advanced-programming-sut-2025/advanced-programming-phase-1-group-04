package Model.Plants;

import Model.App;
import Model.Map.Item;
import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Game;

import java.util.ArrayList;

public class Tree implements Item {

    private final TreeType type;
    private boolean isBurned;
    private DateAndTime plantingDate;
    private DateAndTime lastTimeHarvested;

    //constructor:
    public Tree (TreeType type, DateAndTime plantingDate) {
        this.type = type;
        this.plantingDate = plantingDate;
        isBurned = false;
        lastTimeHarvested = null;
    }

    //getters:
    public TreeType getType() {
        return type;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        //TODO: nemidoonammmmmmmmmmmmmmm
        return 0;
    }

    public SaplingType getSource() {
        return type.getSource();
    }

    public int[] getStages() {
        return type.getStages();
    }

    public int getTotalHarvestTime() {
        return type.getTotalHarvestTime();
    }

    public FruitType getFruitType() {
        return type.getFruitType();
    }

    public int getFruitHarvestCycle() {
        return type.getFruitHarvestCycle();
    }

    public ArrayList<Season> getSeasons() {
        return type.getSeasons();
    }

    public boolean isBurned() {
        return isBurned;
    }

    public DateAndTime getPlantingDate() {
        return plantingDate;
    }

    public DateAndTime getLastTimeHarvested() {
        return lastTimeHarvested;
    }

    public int getCurrentStage () {
        int daysSincePlanted = App.getCurrentGame().getCurrentTime().getDay() - plantingDate.getDay();
        if (daysSincePlanted < 7) {
            return 1;
        }
        else if (daysSincePlanted < 14) {
            return 2;
        }
        else if (daysSincePlanted < 21) {
            return 3;
        }
        else if (daysSincePlanted < 28) {
            return 4;
        }
        return 5;
    }

    public boolean hasFruit () {
        if (lastTimeHarvested != null) {
            if (App.getCurrentGame().getCurrentTime().getDay() - lastTimeHarvested.getDay() >= getFruitHarvestCycle()) {
                return true;
            }
        }
        if (getCurrentStage() == 5) {
            return true;
        }
        return false;
    }

    //setters:
    public void burn() {
        isBurned = true;
    }

    public void setPlantingDate(DateAndTime plantingDate) {
        this.plantingDate = plantingDate;
    } //TODO: in bashe? ya final konim?

    public void setLastTimeHarvested(DateAndTime lastTimeHarvested) {
        this.lastTimeHarvested = lastTimeHarvested;
    }
}
