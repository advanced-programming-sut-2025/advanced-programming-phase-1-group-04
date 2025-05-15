package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Result;
import Model.Time.Weather;

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
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return (int) (type.getEnergyConsumption() * 1.5);
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return type.getEnergyConsumption() * 2;
        }
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
