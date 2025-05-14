package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Game;
import Model.Map.Item;
import Model.Map.ItemType;
import Model.Map.Stone;
import Model.Map.Wood;
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
        if (v > App.getCurrentGame().getCurrentPlayer().getMaxEnergy()) {
            App.getCurrentGame().getCurrentPlayer().setMaxEnergy(v);
        }
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
        if (inventory.getItems().isEmpty()) {
            return new Result(true, "hich ani nadari");
        }
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
            return new Result(true, "one " + itemName.toLowerCase() + " has been removed from inventory.");
        }
        return  new Result(false, quantity + " " + itemName.toLowerCase() + "s have been remove from inventory.");
    }

    public static Result inventoryTrashWithoutNumber (String itemName) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        if (!App.getCurrentGame().getCurrentPlayer().removeItemFromInventory(itemName, -1)) {
            return new Result(false, "you don't have " +
                    itemName + " in your inventory!");
        }
        return new Result(false,  itemName.toLowerCase() + " has been completely remove from inventory.");
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

    public static Result cheatItem(String type, String name, String stringCount) {
        int count = Integer.parseInt(stringCount);
        Item item = getItemByTypeName(type, name);

        if (App.getCurrentGame().getCurrentPlayer().getInventoryCapacity() > count) { // TODO: نمیدونم چطور مشخص میکنی جا داره یا نه ولی ارور مربوطه
            return new Result(false, "");
        } else if (count < 1) {
            return new Result(false, "Count must be a positive number!");
        } else if (!isTypeValid(type)) {
            StringBuilder validType = new StringBuilder();
            for (ItemType itemType: ItemType.values()) {
                validType.append(itemType.getName()).append(" ");
            }
            return new Result(false, "Type is invalid. Valid types: {" + validType + "}");
        } else if (item == null) {
            return new Result(false, "Name is invalid!");
        }

        App.getCurrentGame().getCurrentPlayer().getInventory().addItem(item, count);
        return new Result(true, "Now you have " + count +" of " + name + " in your inventory");
    }

    public static boolean isParsableInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isTypeValid(String type) {
        for (ItemType itemType: ItemType.values()) {
            if (itemType.getName().equals(type))
                return true;
        }
        return false;
    }

    private static Item getItemByTypeName(String type, String name) {
        //TODO: change some constructors and return items for each type
        switch (type) {
            case "tree":
            case "sapling":
            case "crop":
            case "fruit":
            case "seed":
            case "foraging crop":
            case "foraging mineral":
            case "stone":
                if (name.equals("stone")) return new Stone();
                else return null;
            case "wood":
                if (name.equals("wood")) return new Wood();
                else return null;
            case "craft":
            case "food":
            case "fish":
            case "animal product":
            default:
                return null;
        }
    }
}
