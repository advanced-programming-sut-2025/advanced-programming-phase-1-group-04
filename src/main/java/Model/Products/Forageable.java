package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public interface Forageable {
    public boolean isForageable();
    public ArrayList<Season> getSeasons();
}
