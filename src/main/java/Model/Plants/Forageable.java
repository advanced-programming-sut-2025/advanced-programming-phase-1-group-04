package Model.Plants;

import Model.Enum.Season;

import java.util.ArrayList;

public interface Forageable {
    boolean isForageable();
    ArrayList<Season> getSeasons();
}
