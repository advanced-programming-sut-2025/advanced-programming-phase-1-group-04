package io.Ap.StardewValley.Controller.SirkBozorg;

import io.Ap.StardewValley.Controller.GameMenuController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Crafting.Craft;
import io.Ap.StardewValley.Model.Crafting.CraftRecipe;
import io.Ap.StardewValley.Model.Crafting.CraftType;
import io.Ap.StardewValley.Model.Map.*;
import io.Ap.StardewValley.Model.Map.BuildingType;
import io.Ap.StardewValley.Model.Map.Item;
import io.Ap.StardewValley.Model.Map.Tile;
import io.Ap.StardewValley.Model.Player.Player;
import io.Ap.StardewValley.Model.Result;

public class CraftController {
    public static Result showRecipes () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
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
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.House) {
            return new Result(false, "you must be at home for using this command!");
        }
        CraftRecipe recipe;
        if (craftName == null) {
            return new Result(false, "craft name is invalid");
        }
        if((recipe = findCraftRecipe(craftName)) == null) {
            return new Result(false, "you haven't learned this recipe yet!");
        }
        if (App.getCurrentGame().getCurrentPlayer().getInventory().getRemainedCapacity() <= 0) {
            return new Result(false, "your inventory doesn't have enough capacity!");
        }
        boolean success = canMakeCraft(recipe).isSuccessful();
        if (!success) {
            return canMakeCraft(recipe);
        }
        for (Item i : recipe.getRecipe().keySet()) {
            App.getCurrentGame().getCurrentPlayer().removeItemFromInventory(i.getName(), recipe.getRecipe().get(i));
        }
        if (findCraftType(recipe) == null) {
            return new Result(false, "invalid craft name!"); //never happens (inshallah)
        }
        if (App.getCurrentGame().getCurrentPlayer().getEnergy() < 2) {
            return new Result(false, "you don't have enough energy\n" + GameMenuController.nextTurn().message());
            //TODO: next turn call mishe?
        }
        App.getCurrentGame().getCurrentPlayer().addEnergy(-2);
        App.getCurrentGame().getCurrentPlayer().addItemToInventory(new Craft(findCraftType(recipe)), 1);
        return new Result(false, craftName + " added to inventory.");
    }

    public static Result placeCraft (String craftName, String direction) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (direction == null) {
            return new Result(false, "invalid direction!");
        }
        Tile tile = GameMenuController.getTileByDirection(direction);
        Craft craft;

        if (tile == null) {
            return new Result(false, "invalid direction!");
        }
        if (tile.getType() != TileType.Ground) {
            return new Result(false, "the selected tile is not ground!");
        }
        if (craftName == null) {
            return new Result(false, "invalid craft name!");
        }
        if ((craft = PlayerController.getCraft(craftName)) == null) {
            return new Result(false, "there's no such craft!");
        }
        if ((player.getInventory().hasItemWithName(craftName)) == null) {
            return new Result(false, "you don't have this craft in your inventory!");
        }
        if (tile.getItem() != null) {
            return new Result(false, "the selected tile isn't empty!");
        }
        tile.setItem(craft);
        App.getCurrentGame().getCurrentPlayer().removeItemFromInventory(craft.getName(), 1);
        return new Result(false, craft.getName() + " has been successfully placed in the selected tile.");
    }

    public static Result cheatAddItem (String itemName, String count, String type) {
        return PlayerController.cheatItem(type, itemName, count);
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

    public static CraftType findCraftType (CraftRecipe recipe) {
        for (CraftType c : CraftType.values()) {
            if (c.getIngredient() == recipe) {
                return c;
            }
        }
        return null;
    }

    public static CraftType findCraftTypeFromAllRecipes (String name) {
        if (name == null) return null;
        for (CraftType t : CraftType.values()) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}
