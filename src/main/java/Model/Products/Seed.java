package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class Seed {
    private final String name;
    private final ArrayList<Season> seasons;
    private final CropType crop;

    public Seed (String name, ArrayList<Season> season, CropType crop) {
        this.name = name;
        this.seasons = season;
        this.crop = crop;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public CropType getCrop() {
        return crop;
    }
}
