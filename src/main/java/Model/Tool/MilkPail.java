package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Result;
import Model.Time.Weather;

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
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return 6;
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return 8;
        }
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
