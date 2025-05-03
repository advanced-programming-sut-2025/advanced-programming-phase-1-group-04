package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public class ForagingMineral implements Forageable{
    private final String name;
    private final int sellPrice;

    //constructor:
    ForagingMineral (String name, int sellPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
    }

    //getters:
    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter));
    }

    @Override
    public boolean isForageable () {
        return true;
    }
}
