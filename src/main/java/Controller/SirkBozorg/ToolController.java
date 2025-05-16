package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Map.Item;
import Model.Result;
import Model.Tool.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToolController {
    public static Result equip (String toolName) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        List<Tool> tools = extractTools(App.getCurrentGame().getCurrentPlayer().getInventory().getItemList());
        Tool tool = findToolByName(tools, toolName);
        if (tool == null) {
            return new Result(false, "you don't have this tool in your inventory!");
        }
        else {
            App.getCurrentGame().getCurrentPlayer().setCurrentTool(tool);
            return new Result(true, "you're now equipped with a " + toolName.toLowerCase());
        }
    }

    public static Result showCurrentTool () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        if (App.getCurrentGame().getCurrentPlayer().getCurrentTool() == null) {
            return new Result(false, "you are equipped with no tools right now!\nuse tools equip command.");
        }
        return new Result(true, "your current tool is " + App.getCurrentGame().getCurrentPlayer().
                getCurrentTool().getName());
    }

    public static Result showAvailableTools () {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        List<Tool> tools = extractTools(App.getCurrentGame().getCurrentPlayer().getInventory().getItemList());
        String result = "your available tools are:";
        for (Tool tool : tools) {
            result = result + "\n" + tool.getName();
        }
        return new Result (true, result);
    }

    public static Result upgradeTool (String toolName) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        List<Tool> tools = extractTools(App.getCurrentGame().getCurrentPlayer().getInventory().getItemList());
        Tool tool = findToolByName(tools, toolName);
        if (tool == null) {
            return new Result(false, "you don't have this tool in your inventory!");
        }
        else {
            return tool.upgrade();
        }
    }

    public static Result useTool (String direction) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        if (App.getCurrentGame().getCurrentPlayer().getCurrentTool() == null) {
            return new Result (false, "you are not equipped with any tool yet!");
        }
        return App.getCurrentGame().getCurrentPlayer().getCurrentTool().
                use(GameMenuController.getTileByDirection(direction));
    }



    public static List<Tool> extractTools(List<Item> items) {
        return items.stream()
                .filter(item -> item instanceof Tool)
                .map(item -> (Tool) item)
                .collect(Collectors.toList());
    }

    public static Tool findToolByName(List<Tool> tools, String inputName) {
        if (tools == null) {
            return null;
        }
        return tools.stream()
                .filter(tool -> tool.getName().equalsIgnoreCase(inputName))
                .findFirst()
                .orElse(null);
    }
}