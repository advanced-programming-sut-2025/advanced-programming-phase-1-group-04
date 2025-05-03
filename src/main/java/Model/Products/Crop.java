package Model.Products;

import Model.DateAndTime;
import Model.Enum.Season;
import Model.Game;

import java.util.ArrayList;

public class Crop {
    private final String name;
    private final SeedType source;
    private final int[] stages;
    private final int harvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private ArrayList<Season> seasons = new ArrayList<>();
    private final boolean canBecomeGiant;
    private DateAndTime plantingDate;
    private DateAndTime lastTimeHarvested;
    private boolean isMixed;

    //constructor:
    private Crop (CropBuilder builder) {
        this.name = builder.name;
        this.source = builder.source;
        this.stages = builder.stages;
        this.harvestTime = builder.harvestTime;
        this.oneTime = builder.oneTime;
        this.regrowthTime = builder.regrowthTime;
        this.baseSellPrice = builder.baseSellPrice;
        this.isEdible = builder.isEdible;
        this.energy = builder.energy;
        this.seasons = builder.seasons;
        this.canBecomeGiant = builder.canBecomeGiant;
        this.plantingDate = null;
        this.lastTimeHarvested = null;
        this.isMixed = builder.isMixed;

    }


    //getters:
    public String getName() {
        return name;
    }

    public SeedType getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public int getHarvestTime() {
        return harvestTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public DateAndTime getPlantingDate() {
        return plantingDate;
    }

    public DateAndTime getLastTimeHarvested() {
        return lastTimeHarvested;
    }

    public int getCurrentStage () {
        int stageTime = harvestTime;
        int currentStage = stages.length + 1;
        int daysSincePlanted = Game.getCurrentTime().getDay() - plantingDate.getDay();
        if (lastTimeHarvested == null) {
            if (daysSincePlanted >= stageTime) {
                return currentStage;
            }
            for (int i : stages) {
                currentStage--;
                stageTime -= i;
                if (daysSincePlanted >= stageTime) {
                    return currentStage;
                }
            }
        }
        else {
            if (Game.getCurrentTime().getDay() - lastTimeHarvested.getDay() >= regrowthTime) {
                return stages.length;
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
    }

    public void setLastTimeHarvested(DateAndTime lastTimeHarvested) {
        this.lastTimeHarvested = lastTimeHarvested;
    }


    //builder:
    public static class CropBuilder {
        private String name;
        private SeedType source;
        private int[] stages;
        private int harvestTime;
        private boolean oneTime;
        private int regrowthTime;
        private int baseSellPrice;
        private boolean isEdible;
        private int energy;
        private ArrayList<Season> seasons;
        private boolean canBecomeGiant;
        private boolean isMixed = false;

        public CropBuilder setName (String name) {
            this.name = name;
            return this;
        }


        public CropBuilder setSource(SeedType source) {
            this.source = source;
            return this;
        }

        public CropBuilder setStages(int[] stages) {
            this.stages = stages;
            return this;
        }

        public CropBuilder setHarvestTime(int harvestTime) {
            this.harvestTime = harvestTime;
            return this;
        }

        public CropBuilder setOneTime(boolean oneTime) {
            this.oneTime = oneTime;
            return this;
        }

        public CropBuilder setRegrowthTime(int regrowthTime) {
            this.regrowthTime = regrowthTime;
            return this;
        }

        public CropBuilder setBaseSellPrice(int baseSellPrice) {
            this.baseSellPrice = baseSellPrice;
            return this;
        }

        public CropBuilder setEdible(boolean edible) {
            isEdible = edible;
            return this;
        }

        public CropBuilder setEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public CropBuilder setSeasons(ArrayList<Season> seasons) {
            this.seasons = seasons;
            return this;
        }

        public CropBuilder setCanBecomeGiant(boolean canBecomeGiant) {
            this.canBecomeGiant = canBecomeGiant;
            return this;
        }

        public CropBuilder setIsMixed (boolean isMixed) {
            this.isMixed = isMixed;
            return this;
        }

        public Crop build () {
            return new Crop(this);
        }
    }

}
