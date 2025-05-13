package Model.Tool;

import Model.Map.Tile;
import Model.Result;

public class FishingPole implements Tool{
    private FishingPoleType type;

    public FishingPole (FishingPoleType type) {
        this.type = type;
    }

    @Override
    public Result upgrade() {
        //TODO
        // related to shops and inventory
        return new Result(false, "TODO");
    }

    @Override
    public Result use(Tile tile) {
        return new Result(false, "TODO");
        //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        return type.getEnergyConsumption();
    }

    @Override
    public String getLevelString() {
        return type.name();
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
