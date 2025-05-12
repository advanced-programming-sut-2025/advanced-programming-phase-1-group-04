package Controller.SirkBozorg;

import Model.App;
import Model.Game;
import Model.Map.Item;
import Model.Player.Inventory;
import Model.Player.Player;
import Model.Player.Skill;
import Model.Result;

public class PlayerController {

    public static Result showEnergy () {
        return new Result(true, "player " + App.getCurrentGame().getCurrentPlayer().getUsername() + " energy: " +
                App.getCurrentGame().getCurrentPlayer().getEnergy());
    }

    public static Result cheatEnergy (String value) {
        int v;
        if (value == null || (v = Integer.parseInt(value)) < 0) {
            return new Result (false, "value must be a non-negative number!");
        }
        App.getCurrentGame().getCurrentPlayer().setEnergy(v);
        return new Result (true, "player " + App.getCurrentGame().getCurrentPlayer().getUsername() +
                " energy is now " + value);
    }

    public static Result unlimitedEnergy () {
        //TODO......................................................................................
        return new Result (true, "your max energy is now infinite.");
    }

    public static Result showInventory () {
        String result = "items in inventory:\n";
        Inventory inventory = App.getCurrentGame().getCurrentPlayer().getInventory();
        for (Item item : inventory.getItems().keySet()) {
            result = result + item.getName() + " : " + inventory.getItems().get(item) + "\n";
        }

        return new Result(true, result);
    }

    public static Result inventoryTrash (Item item, int number) {
        //TODO................................................................................
        // tool ro bezan
    }

    public static Result showAbility() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        String result = "ability levels:\nfarming : " + player.getAbilityLevel(Skill.Farming) +
                "\nmining : " + player.getAbilityLevel(Skill.Mining) + "\nforaging : " +
                player.getAbilityLevel(Skill.Foraging) + "\nfishing : " + player.getAbilityLevel(Skill.Fishing);

        return new Result(true, result);

    }



}
