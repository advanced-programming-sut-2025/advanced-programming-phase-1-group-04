package io.Ap.StardewValley.Model.Plants;

import io.Ap.StardewValley.Model.Time.Season;

import java.util.ArrayList;

public interface Forageable {
    boolean isForageable();
    ArrayList<Season> getSeasons();
}
