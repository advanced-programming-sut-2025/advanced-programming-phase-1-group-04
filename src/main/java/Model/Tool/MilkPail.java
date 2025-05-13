package Model.Tool;

import Model.Map.Tile;
import Model.Result;

public class MilkPail implements Tool{
    @Override
    public Result upgrade() {
        return new Result(false, "milk pail can't get upgraded!");
    }

    @Override
    public Result use(Tile tile) {
        return new Result(false, "TODO"); //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        return 4;
    }

    @Override
    public String getLevelString() {
        return "Milk pail";
    }

    @Override
    public String getName() {
        return "Milk pail";
    }

    @Override
    public int getPrice() {
        return 1000;
    }
}
