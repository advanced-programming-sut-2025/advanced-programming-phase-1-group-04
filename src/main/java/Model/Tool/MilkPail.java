package Model.Tool;

import Controller.SirkBozorg.AnimalController;
import Model.Animals.Animal;
import Model.Animals.AnimalType;
import Model.App;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Player.Skill;
import Model.Result;
import Model.Time.Weather;

public class MilkPail implements Tool{
    ToolType type = ToolType.MilkPail;
    @Override
    public Result upgrade() {
        return new Result(false, "milk pail can't get upgraded!");
    }

    @Override
    public Result use(Tile tile) {

        return new Result(false, "TODO"); //TODO
    }

    @Override
    public Result use(Coordinate c) {
        if (c == null) {
            return new Result(false, "invalid coordinate!");
        }
        for (Animal a : App.getCurrentGame().getCurrentPlayer().getMyAnimals()) {
            if (c.equals(a.getCoordinate()) && a.getType() == AnimalType.Cow) {
                return AnimalController.collectAnimalProduce(a.getName());
            }
        }
        return new Result(false, "there's no cow in this tile!");

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

    public static int getEnergyForAnimals() {
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
        return 0;
    }

    @Override
    public ToolType getType() {
        return type;
    }
}
