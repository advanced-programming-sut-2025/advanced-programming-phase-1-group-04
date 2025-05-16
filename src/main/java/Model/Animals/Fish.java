package Model.Animals;

import Model.Map.Item;
import Model.Time.Season;

public class Fish implements Item {
    private final FishType type;
    private final double quality;

    public Fish(FishType type, double quality) {
        this.type = type;
        this.quality = quality;
    }

    @Override
    public int getPrice() {
        double coefficient = 1;
        if (this.quality < 0.7) {
            coefficient = 1.25;
        } else if (this.quality < 0.9) {
            coefficient = 1.5;
        } else if (this.quality >= 0.9) {
            coefficient = 2;
        }
        return (int) (type.getBasePrice() * coefficient);
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
