package Model.Plants;

import Model.Time.Season;

import java.util.ArrayList;

public interface Forageable {
    boolean isForageable();
    ArrayList<Season> getSeasons();
}
