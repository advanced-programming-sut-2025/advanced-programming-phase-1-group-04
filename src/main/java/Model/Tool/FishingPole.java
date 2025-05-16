package Model.Tool;

import Model.App;
import Model.Map.Tile;
import Model.Result;
import Model.Time.Weather;

public class FishingPole implements Tool{
    ToolType type = ToolType.FishingPole;
    private FishingPoleType level;

    public FishingPole (FishingPoleType type) {
        this.level = type;
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
            return (int) (level.getEnergyConsumption() * 1.5);
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            return level.getEnergyConsumption() * 2;
        }
        return level.getEnergyConsumption();
    }

    @Override
    public String getLevelString() {
        return level.name();
    }

    @Override
    public String getName() {
        return "Fishing pole";
    }

    @Override
    public int getPrice() {
        return level.getPrice();
    }

    @Override
    public ToolType getType() {
        return type;
    }
}
