package Model.Tool;

import Controller.SirkBozorg.AnimalController;
import Model.Animals.Animal;
import Model.Animals.AnimalType;
import Model.App;
import Model.Map.Coordinate;
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
        return new Result(false, "TODO");
    }

    @Override
    public Result use(Coordinate c) {
        if (c == null) {
            return new Result(false, "invalid coordinate!");
        }
        for (Animal a : App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            if (c.equals(a.getCoordinate()) && a.getType() == AnimalType.Sheep) {
                return AnimalController.collectAnimalProduce(a.getName());
            }
        }
        return new Result(false, "there's no sheep in this tile!");
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
        return 0;
    }

    @Override
    public ToolType getType() {
        return type;
    }
}
