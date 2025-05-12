package Controller.SirkBozorg;

import Model.App;
import Model.Map.Item;
import Model.Result;
import Model.Tool.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToolController {
    public static Result equip (String toolName) {
        List<Tool> tools = extractTools(new ArrayList<>(App.getCurrentGame().getCurrentPlayer().
                getInventory().getItems().keySet()));
        Tool tool = findToolByName(tools, toolName);
        if (tool == null) {
            return new Result(false, "you don't have this tool in your inventory!");
        }
        else {
            App.getCurrentGame().getCurrentPlayer().setCurrentTool(tool);
            return new Result(true, "you're now equipped with a " + toolName);
        }
    }

    public static Result showCurrentTool () {
        return new Result(true, "your current tool is " + App.getCurrentGame().getCurrentPlayer().
                getCurrentTool().getName());
    }

    public static Result showAvailableTools () {
        List<Tool> tools = extractTools(new ArrayList<>(App.getCurrentGame().getCurrentPlayer().
                getInventory().getItems().keySet()));
        String result = "your available tools are:";
        for (Tool tool : tools) {
            result = result + "\n" + tool.getName();
        }
        return new Result (true, result);
    }

    public static Result upgradeTool (String toolName) {
        List<Tool> tools = extractTools(new ArrayList<>(App.getCurrentGame().getCurrentPlayer().
                getInventory().getItems().keySet()));
        Tool tool = findToolByName(tools, toolName);
        if (tool == null) {
            return new Result(false, "you don't have this tool in your inventory!");
        }
        else {
            tool.upgrade();
            return new Result(true, toolName + "has been upgraded.\nprevious level: \ncurrent level:");
            //TODO.....................................................
        }
    }

    public static Result useTool () {
        //TODO
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