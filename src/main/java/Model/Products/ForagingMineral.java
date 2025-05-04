package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public class ForagingMineral {
    private final ForagingMineralType type;

    //constructor:
    ForagingMineral (ForagingMineralType type) {
        this.type = type;
    }

    //getters:
    public String getName() {
        return type.getName();
    }

    public int getSellPrice() {
        return type.getSellPrice();
    }

    public ArrayList<Season> getSeasons() {
        return new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter));
    }

    public boolean isForageable () {
        return true;
    }
}
