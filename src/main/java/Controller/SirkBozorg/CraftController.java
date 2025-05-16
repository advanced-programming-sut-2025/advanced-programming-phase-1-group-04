package Controller.SirkBozorg;

import Model.App;
import Model.Crafting.Craft;
import Model.Crafting.CraftRecipe;
import Model.Map.Coordinate;
import Model.Result;

public class CraftController {
    public static Result showRecipes () {
        String result = "Crafting recipes:\n";
        for (CraftRecipe r : App.getCurrentGame().getCurrentPlayer().getCraftRecipes()) {

        }
        return new Result(false, "TODO");
    }

    public void makeCraft (Craft craft) {

    }

    public void placeCraft (Craft craft, Coordinate coordinate) {

    }
}
