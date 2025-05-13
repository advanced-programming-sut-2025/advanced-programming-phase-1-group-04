package Model.Tool;

import Model.Map.Tile;
import Model.Result;

public class Scythe implements Tool {

    @Override
    public Result upgrade() {
        return new Result(false, "scythe can't get upgraded");
    }

    @Override
    public Result use(Tile tile) {
        return new Result(false, "TODO"); //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        return 2;
    }

    @Override
    public String getLevelString() {
        return "Scythe";
    }

    @Override
    public String getName() {
        return "Scythe";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
