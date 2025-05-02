package View;

import Controller.LoginMenuController;
import Model.Command.LoginMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        
        if ((matcher = LoginMenuCommand.Register.isMatch(input)) != null) {
            System.out.println(controller.register(matcher.group("username"), matcher.group("password"),
                    matcher.group("rePassword"), matcher.group("nickname"), matcher.group("email"),
                    matcher.group("gender")));
        } else if ((matcher = LoginMenuCommand.Login.isMatch(input)) != null) {
            System.out.println(controller.login(matcher.group("username"), matcher.group("password"), matcher.group("loggedIn")));
        } else if ((matcher = LoginMenuCommand.ForgetPassword.isMatch(input)) != null) {
            System.out.println(controller.forgetPassword(matcher.group("username")));
        } else if ((matcher = LoginMenuCommand.GoMenu.isMatch(input)) != null) {
            System.out.println(controller.goMenu(matcher.group("menu")));
        } else if (LoginMenuCommand.CurrentMenu.isMatch(input) != null) {
            System.out.println(controller.currentMenu());
        } else if (LoginMenuCommand.Exit.isMatch(input) != null) {
            System.out.println(controller.exitMenu());
        }
    }
}
