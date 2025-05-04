package Controller;

import Model.App;
import Model.Command.Menu;
import Model.Result;

public class GameMenuController {
    public static Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in game menu");
        }

        
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in game menu");
    }

    public static Result exitMenu () {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in main menu");
    }
}
