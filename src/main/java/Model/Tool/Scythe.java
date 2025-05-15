package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Result;
import Model.Time.Weather;

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
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            return 3;
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return 4;
        }
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
