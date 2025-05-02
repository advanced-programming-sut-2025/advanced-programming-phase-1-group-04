package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class ForagingCrop {
    private final String name;
    private final ArrayList<Season> seasons;
    private final int baseSellPrice;
    private final int energy;

    public ForagingCrop (ForagingCropBuilder builder) {
        this.name = builder.name;
        this.seasons = builder.seasons;
        this.baseSellPrice = builder.baseSellPrice;
        this.energy = builder.energy;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Season> getSeason() {
        return seasons;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public int getEnergy() {
        return energy;
    }


    public static class ForagingCropBuilder {
        private String name;
        private ArrayList<Season> seasons;
        private int baseSellPrice;
        private int energy;

        public ForagingCropBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ForagingCropBuilder setSeasons(ArrayList<Season> seasons) {
            this.seasons = seasons;
            return this;
        }

        public ForagingCropBuilder setBaseSellPrice(int baseSellPrice) {
            this.baseSellPrice = baseSellPrice;
            return this;
        }

        public ForagingCropBuilder setEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public ForagingCrop build() {
            return new ForagingCrop (this);
        }
    }
}
