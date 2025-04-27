package View;

import Controller.GameMenuController;
import Model.Command.GameMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    GameMenuController controller = new GameMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        // TODO: tehkhhhh
        
        if ((matcher = GameMenuCommand.GoMenu.isMatch(input)) != null) {
            System.out.println(controller.goMenu(matcher.group("menu")));
        } else if (GameMenuCommand.CurrentMenu.isMatch(input) != null) {
            System.out.println(controller.currentMenu());
        } else if (GameMenuCommand.Exit.isMatch(input) != null) {
            System.out.println(controller.exitMenu());
        }
    }
}
