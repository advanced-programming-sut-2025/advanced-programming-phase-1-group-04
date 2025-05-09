package Model.Plants;

import Model.Enum.Season;

import java.util.ArrayList;

public class Seed{
    //TODO: mixed seeds must be controlled in controller. no specific operation has been considered yet.

    private final SeedType seedType;

    public Seed (SeedType seedType) {
        this.seedType = seedType;
    }

    public SeedType getSeedType() {
        return seedType;
    }

    public String getName() {
        return seedType.getName();
    }

    public ArrayList<Season> getSeasons() {
        return seedType.getSeasons();
    }

    public CropType getCrop() {
        return seedType.getCrop();
    }

    public boolean isForageable () {
        return true;
    }
}