package Controller;

import Model.App;
import Model.Command.Menu;
import Model.Result;

public class MainMenuController {
    public Result logout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menu.LoginMenu);

        return new Result(true, "You have successfully logged out. Now you are in login menu");
    }

    public Result goMenu (String menu) {
        if (!menu.equals("game") && !menu.equals("profile")) {
            return new Result(false, "You can't go to " + menu + " menu in login menu");
        }

        if (menu.equals("game")) App.setCurrentMenu(Menu.GameMenu);
        else App.setCurrentMenu(Menu.ProfileMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public Result currentMenu () {
        return new Result(true, "You are in main menu");
    }

    public Result exitMenu () {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "Now you are in login menu");
    }
}
