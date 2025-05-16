package Controller.SirkBozorg;

import Model.App;
import Model.Crafting.Craft;
import Model.Crafting.CraftRecipe;
import Model.Map.Coordinate;
import Model.Map.Item;
import Model.Result;

public class CraftController {
    public static Result showRecipes () {
        String result = "Crafting recipes:\n";
        for (CraftRecipe r : App.getCurrentGame().getCurrentPlayer().getCraftRecipes()) {
            result = result + r.getName() + ": " + r.getRecipeString() + "\n";
            if (canMakeCraft(r).isSuccessful()) {
                result = result + "needed items are available\n\n";
            }
            else {
                result = result + "needed items are unavailable\n\n";
            }
        }
        return new Result(true, result);
    }

    public static Result makeCraft (String craftName) {
//        CraftRecipe recipe;
//        if (craftName == null) {
//            return new Result(false, "craft name is invalid");
//        }
//        if((recipe = findCraftRecipe(craftName)) == null) {
//            return new Result(false, "you haven't learned this recipe yet!");
//        }
//        boolean success = canMakeCraft(recipe).isSuccessful();
//        if (!success) {
//            return canMakeCraft(recipe);
//        }
//        for (Item i : recipe.getRecipe().keySet()) {
//            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory(i.getName(), )
//        }
        return new Result(false, "todo");
    }

    public void placeCraft (Craft craft, Coordinate coordinate) {

    }

    public static CraftRecipe findCraftRecipe (String name) {
        for (CraftRecipe r : App.getCurrentGame().getCurrentPlayer().getCraftRecipes()) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    public static Result canMakeCraft (CraftRecipe r) {
        for (Item i : r.getRecipe().keySet()) {
            if (!App.getCurrentGame().getCurrentPlayer().getInventory().hasItemWithNumber(i.getName(), r.getRecipe().get(i))) {
                return new Result (false, "you don't have enough " + i.getName() + " in your inventory!");
            }
        }
        return new Result(true, "everything's available.");
    }
}
