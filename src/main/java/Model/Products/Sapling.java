package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class Sapling {
    private final String name;
    private final ArrayList<Season> seasons;
    private final TreeType tree;

    public Sapling (String name, ArrayList<Season> season, TreeType tree) {
        this.name = name;
        this.seasons = season;
        this.tree = tree;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public TreeType getTree() {
        return tree;
    }
}
