package Model.Products;

import Model.Enum.Season;

public class Crop {
    private CropType name;
    private Seed source;
    private int[] stages;
    private int harvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private Season season;
    private boolean canBecomeGiant;

}
