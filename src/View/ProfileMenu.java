package View;

import Controller.ProfileMenuController;
import Model.Command.ProfileMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu {
    ProfileMenuController controller =  new ProfileMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        
        if ((matcher = ProfileMenuCommand.ChangeUsername.isMatch(input)) != null) {
            System.out.println(controller.changeUsername(matcher.group("username")));
        } else if ((matcher = ProfileMenuCommand.ChangeNickname.isMatch(input)) != null) {
            System.out.println(controller.changeNickname(matcher.group("nickname")));
        } else if ((matcher = ProfileMenuCommand.ChangeEmail.isMatch(input)) != null) {
            System.out.println(controller.changeEmail(matcher.group("email")));
        } else if ((matcher = ProfileMenuCommand.ChangePassword.isMatch(input)) != null) {
            System.out.println(controller.changePassword(matcher.group("newPassword"), matcher.group("oldPassword")));
        } else if (ProfileMenuCommand.ShowInfo.isMatch(input) != null) {
            System.out.println(controller.showInfo());
        } else if ((matcher = ProfileMenuCommand.GoMenu.isMatch(input)) != null) {
            System.out.println(controller.goMenu(matcher.group("menu")));
        } else if (ProfileMenuCommand.CurrentMenu.isMatch(input) != null) {
            System.out.println(controller.currentMenu());
        } else if (ProfileMenuCommand.Exit.isMatch(input) != null) {
            System.out.println(controller.exitMenu());
        }
    }
}