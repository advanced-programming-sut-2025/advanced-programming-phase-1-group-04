package Model.Tool;

import Model.Map.Tile;

public class Scythe implements Tool {

    @Override
    public void upgrade() {}

    @Override
    public boolean use(Tile tile) {
        return false; //TODO
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
