package Controller;

import Model.App;
import Model.Command.Menu;
import Model.Result;

public class LoginMenuController {
    public Result register(String username, String password, String rePassword, String nickName, String email, String gender) {

        return new Result(true, "");
    }

    public Result securityQuestion(int number, String answer, String reAnswer) {

        return new Result(true, "");
    }

    public Result login(String username, String password, String loggedIn) {

        return new Result(true, "");
    }

    public Result forgetPassword(String username) {

        return new Result(true, "");
    }

    public Result securityAnswer(String answer) {

        return new Result(true, "");
    }

    public Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in login menu");
        }

        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public Result currentMenu () {
        return new Result(true, "You are in login menu");
    }

    public Result exitMenu () {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Bye bye");
    }
}