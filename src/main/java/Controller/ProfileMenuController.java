package Controller;

import Model.App;
import Model.Command.Menu;
import Model.Result;

public class ProfileMenuController {
    public Result changeUsername (String username) {

        return new Result(true, "");
    }

    public Result changeNickname (String nickname) {

        return new Result(true, "");
    }

    public Result changeEmail (String email) {

        return new Result(true, "");
    }

    public Result changePassword (String newPassword, String oldPassword) {

        return new Result(true, "");
    }

    public Result showInfo() {

        return new Result(true, "");
    }

    public Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in profile menu");
        }

        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public Result currentMenu () {
        return new Result(true, "You are in profile menu");
    }

    public Result exitMenu () {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in main menu");
    }
}
