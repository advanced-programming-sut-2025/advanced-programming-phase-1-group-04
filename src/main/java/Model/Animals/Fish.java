package Model.Animals;

import Model.Map.Item;
import Model.Time.Season;

public class Fish implements Item {
    private FishType type;

    public Fish(FishType type) {
        this.type = type;
    }

    @Override
    public int getPrice() {
        return type.getBasePrice();
    }

    public Season getSeason() {
        return type.getSeason();
    }

    public boolean isLegendary() {
        return type.isLegendary();
    }

    @Override
    public String getName() {
        return type.getName();
    }
}