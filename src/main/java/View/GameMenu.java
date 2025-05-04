package View;

import Controller.GameMenuController;
import Model.Command.GameMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        // TODO: tehkhhhh
        
        if ((matcher = GameMenuCommand.GoMenu.getMatcher(input)) != null) {
            System.out.println(GameMenuController.goMenu(matcher.group("menu")));
        } else if (GameMenuCommand.CurrentMenu.getMatcher(input) != null) {
            System.out.println(GameMenuController.currentMenu());
        } else if (GameMenuCommand.Exit.getMatcher(input) != null) {
            System.out.println(GameMenuController.exitMenu());
        } else {
            System.out.println("invalid command");
        }
    }
}
