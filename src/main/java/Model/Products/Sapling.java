package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class Sapling implements Forageable{
    private final String name;
    private final ArrayList<Season> seasons;
    private final TreeType tree;
    private final boolean isForageable;

    public Sapling (String name, ArrayList<Season> season, TreeType tree, boolean isForageable) {
        this.name = name;
        this.seasons = season;
        this.tree = tree;
        this.isForageable = isForageable;
    }

    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public TreeType getTree() {
        return tree;
    }

    @Override
    public boolean isForageable() {
        return isForageable;
    }
}
