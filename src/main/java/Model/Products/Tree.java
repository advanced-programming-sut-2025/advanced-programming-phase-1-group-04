package Model.Products;

import Model.DateAndTime;
import Model.Enum.Season;
import Model.Game;

import java.util.ArrayList;

public class Tree {
    private final String name;
    private final SaplingType source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final FruitType fruit;
    private final int fruitHarvestCycle;
    private final ArrayList<Season> seasons;
    private boolean isBurned;
    private DateAndTime plantingDate;
    private DateAndTime lastTimeHarvested;

    //constructor:
    public Tree (TreeBuilder builder) {
        this.name = builder.name;
        this.source = builder.source;
        this.stages = new int[] {7, 7, 7, 7};
        this.totalHarvestTime = 28;
        this.fruit = builder.fruit;
        this.fruitHarvestCycle = builder.fruitHarvestCycle;
        this.seasons = builder.seasons;
        isBurned = false;
        lastTimeHarvested = null;
    }


    //getters:
    public String getName() {
        return name;
    }

    public SaplingType getSource() {
        return source;
    }

    public int[] getStages() {
        return stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public FruitType getFruit() {
        return fruit;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
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
        int daysSincePlanted = Game.getCurrentTime().getDay() - plantingDate.getDay();
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
            if (Game.getCurrentTime().getDay() - lastTimeHarvested.getDay() >= fruitHarvestCycle) {
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
    }

    public void setLastTimeHarvested(DateAndTime lastTimeHarvested) {
        this.lastTimeHarvested = lastTimeHarvested;
    }


    //builder:
    public static class TreeBuilder {
        private String name;
        private SaplingType source;
        private ArrayList<Season> seasons;
        private FruitType fruit;
        private int fruitHarvestCycle;

        public Tree.TreeBuilder setName (String name) {
            this.name = name;
            return this;
        }


        public Tree.TreeBuilder setSource(SaplingType source) {
            this.source = source;
            return this;
        }


        public Tree.TreeBuilder setSeasons(ArrayList<Season> seasons) {
            this.seasons = seasons;
            return this;
        }

        public Tree.TreeBuilder setFruit (FruitType fruit) {
            this.fruit = fruit;
            return this;
        }

        public Tree.TreeBuilder setFruitHarvestCycle (int fruitHarvestCycle) {
            this.fruitHarvestCycle = fruitHarvestCycle;
            return this;
        }

        public Tree build () {
            return new Tree(this);
        }
    }
}
