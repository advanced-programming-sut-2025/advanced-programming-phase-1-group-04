package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Game;
import Model.Map.Item;
import Model.Player.Inventory;
import Model.Player.Player;
import Model.Player.Skill;
import Model.Result;

public class PlayerController {

    public static Result showEnergy () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        return new Result(true, "player " + App.getCurrentGame().getCurrentPlayer().getUsername() + " energy: " +
                App.getCurrentGame().getCurrentPlayer().getEnergy());
    }

    public static Result cheatEnergy (String value) {
        int v;
        if (!isParsableInt(value) || (v = Integer.parseInt(value)) < 0) {
            return new Result (false, "value must be a non-negative number!");
        }
        App.getCurrentGame().getCurrentPlayer().setEnergy(v);
        return new Result (true, "player " + App.getCurrentGame().getCurrentPlayer().getUsername() +
                " energy is now " + value);
    }

    public static Result unlimitedEnergy () {
        App.getCurrentGame().getCurrentPlayer().setMaxEnergy(Integer.MAX_VALUE);
        App.getCurrentGame().getCurrentPlayer().setMaxMovesInTurn(Integer.MAX_VALUE);
        return new Result (true, "your max energy is now infinite.");
    }

    public static Result showInventory () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        String result = "items in inventory:\n";
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        for (Item item : inventory.getItems().keySet()) {
            result = result + item.getName() + " : " + inventory.getItems().get(item) + "\n";
        }

        return new Result(true, result);
    }

    public static Result inventoryTrash (String itemName, String number) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        int quantity;
        if (!isParsableInt(number) || (quantity = Integer.parseInt(number)) <= 0) {
            return new Result(false, "number must be positive!");
        }
        else if (!App.getCurrentGame().getCurrentPlayer().removeItemFromInventory(itemName, quantity)) {
            return new Result(false, "you don't have this amount of " +
                    itemName + " in your inventory!");
        }
        if (quantity == 1) {
            return new Result(true, "one " + itemName + " has been removed from inventory");
        }
        return  new Result(false, quantity + " " + itemName + "s have been remove from inventory");
    }

    public static Result showAbility() {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        Player player = App.getCurrentGame().getCurrentPlayer();
        String result = "ability levels:\nfarming : " + player.getAbilityLevel(Skill.Farming) +
                "\nmining : " + player.getAbilityLevel(Skill.Mining) + "\nforaging : " +
                player.getAbilityLevel(Skill.Foraging) + "\nfishing : " + player.getAbilityLevel(Skill.Fishing);

        return new Result(true, result);

    }


    public static boolean isParsableInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
