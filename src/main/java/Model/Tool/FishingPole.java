package Model.Tool;

import Model.App;
import Model.Map.BuildingType;
import Model.Map.Coordinate;
import Model.Map.Tile;
import Model.Player.Player;
import Model.Player.Skill;
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
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.FishShop) {
            return new Result(false, "you are not in fish shop!");
        }
        if (player.getCurrentTool() == null || !(player.getCurrentTool() instanceof FishingPole pole)) {
            return new Result(false, "you must be equipped with fishing pole first!");
        }
        int cost;
        if (pole.getLevel() == FishingPoleType.Training) {
            cost = FishingPoleType.Bamboo.getPrice();
            if (player.getCount() < cost) {
                return new Result(false, "you don't have enough money!\n" +
                        cost + "g is needed.");
            }
            player.addCount(-1 * cost);
            pole.setLevel(FishingPoleType.Bamboo);
            return new Result(true, "fishing pole upgraded successfully.\ncurrent fishing pole type: " +
                    FishingPoleType.Bamboo);
        }
        if (pole.getLevel() == FishingPoleType.Bamboo) {
            cost = FishingPoleType.Fiberglass.getPrice();
            if (player.getCount() < cost) {
                return new Result(false, "you don't have enough money!\n" +
                        cost + "g is needed.");
            }
            player.addCount(-1 * cost);
            pole.setLevel(FishingPoleType.Fiberglass);
            return new Result(true, "fishing pole upgraded successfully.\ncurrent fishing pole type: " +
                    FishingPoleType.Fiberglass);
        }
        if (pole.getLevel() == FishingPoleType.Fiberglass) {
            cost = FishingPoleType.Iridium.getPrice();
            if (player.getCount() < cost) {
                return new Result(false, "you don't have enough money!\n" +
                        cost + "g is needed.");
            }
            player.addCount(-1 * cost);
            pole.setLevel(FishingPoleType.Iridium);
            return new Result(true, "fishing pole upgraded successfully.\ncurrent fishing pole type: " +
                    FishingPoleType.Iridium);
        }
        return new Result(false, "fishing pole can't get upgraded!\ncurrent fishing pole type: " +
                FishingPoleType.Iridium);
    }

    @Override
    public Result use(Tile tile) {
        return new Result(false, "this command doesn't work for fishing pole!");
    }

    @Override
    public Result use(Coordinate c) {
        if (c == null) {
            return new Result(false, "invalid coordinate!");
        }
        Tile t = App.getCurrentGame().getTile(c);
        return use(t);
    }

    @Override
    public int getEnergyConsumption(boolean useSuccess) {
        int base = 0;
        if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Rain) {
            base = (int) (level.getEnergyConsumption() * 1.5);
        }
        else if (App.getCurrentGame().getCurrentTime().getWeather() == Weather.Snow) {
            base = level.getEnergyConsumption() * 2;
        }
        if (App.getCurrentGame().getCurrentPlayer().isBuffed(Skill.Fishing)) {
            base = Math.max(base - 1, 0);
        }
        return base;
    }

    public FishingPoleType getFishingPoleType() {
        return level;
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

    public FishingPoleType getLevel() {
        return level;
    }

    public void setLevel(FishingPoleType level) {
        this.level = level;
    }
}
