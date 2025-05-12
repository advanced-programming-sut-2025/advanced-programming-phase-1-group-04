package Model.Tool;

import Model.Map.Tile;

public class FishingPole implements Tool{
    private FishingPoleType type;

    public FishingPole (FishingPoleType type) {
        this.type = type;
    }

    @Override
    public void upgrade() {
        //TODO
        // related to shops and inventory
    }

    @Override
    public boolean use(Tile tile) {
        return false;
        //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        return type.getEnergyConsumption();
    }

    @Override
    public String getName() {
        return "Fishing pole";
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }
}
