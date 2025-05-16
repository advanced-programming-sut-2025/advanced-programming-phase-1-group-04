package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Result;
import Model.Time.Weather;

public class Shear implements Tool {
    ToolType type = ToolType.Shear;

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

    @Override
    public ToolType getType() {
        return type;
    }
}
