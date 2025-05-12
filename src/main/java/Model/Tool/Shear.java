package Model.Tool;

import Model.Map.Tile;

public class Shear implements Tool {

    @Override
    public void upgrade() {}

    @Override
    public boolean use(Tile tile) {
        return false; //TODO
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        return 4;
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
