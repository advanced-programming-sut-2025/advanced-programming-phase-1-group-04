package View;

import Controller.MainMenuController;
import Model.Command.MainMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if (MainMenuCommand.Logout.isMatch(input) != null) {
            System.out.println(controller.logout());
        } else if ((matcher = MainMenuCommand.GoMenu.isMatch(input)) != null) {
            System.out.println(controller.goMenu(matcher.group("menu")));
        } else if (MainMenuCommand.CurrentMenu.isMatch(input) != null) {
            System.out.println(controller.currentMenu());
        } else if (MainMenuCommand.Exit.isMatch(input) != null) {
            System.out.println(controller.exitMenu());
        }
    }
}
