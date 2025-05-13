package Model.Tool;

import Model.Map.Tile;
import Model.Result;

public class Shear implements Tool {

    @Override
    public Result upgrade() {
        return new Result(false, "shear can't get upgraded!");
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
        return "Shear";
    }

    @Override
    public String getName() {
        return "Shear";
    }

    @Override
    public int getPrice() {
        return 1000;
    }
}
