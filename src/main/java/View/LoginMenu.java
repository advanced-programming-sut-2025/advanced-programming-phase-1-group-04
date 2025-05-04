package View;

import Controller.LoginMenuController;
import Model.Command.LoginMenuCommand;
import Model.Enum.SecurityQuestion;
import Model.Result;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) throws IOException {
        String input = scanner.nextLine();
        Matcher matcher;
        
        if ((matcher = LoginMenuCommand.Register.getMatcher(input)) != null) {
            Result result = LoginMenuController.register(matcher.group("username"), matcher.group("password"),
                    matcher.group("rePassword"), matcher.group("nickname"), matcher.group("email"),
                    matcher.group("gender"));
            System.out.println(result);
            // TODO: Badtarin rah
            if (result.message().contains("Do you accept")) {
                String newPassword = result.message().substring(20, result.message().indexOf("\n")).trim();
                input = scanner.nextLine();
                if (input.contains("yse")) {
                    LoginMenuController.register(matcher.group("username"), newPassword,
                            newPassword, matcher.group("nickname"), matcher.group("email"),
                            matcher.group("gender"));
                } else {
                  System.out.println("Bashe Pas");
                }
            }
            if (result.isSuccessful()) {
                String username = matcher.group("username");
                input = scanner.nextLine();
                while (true) {
                    if ((matcher = LoginMenuCommand.SecurityQuestion.getMatcher(input)) != null) {
                        result = LoginMenuController.securityQuestion(username,  Integer.parseInt(matcher.group("questionNumber")), matcher.group("answer"), matcher.group("reAnswer"));
                        System.out.println(result);
                        if (result.isSuccessful()) break;
                    } else {
                        System.out.println("Please pick a security question!\n" + SecurityQuestion.getQuestions());
                    }
                    input = scanner.nextLine();
                }
            }
        } else if ((matcher = LoginMenuCommand.Login.getMatcher(input)) != null) {
            System.out.println(LoginMenuController.login(matcher.group("username"), matcher.group("password"), matcher.group("loggedIn")));
        } else if ((matcher = LoginMenuCommand.ForgetPassword.getMatcher(input)) != null) {
            System.out.println(LoginMenuController.forgetPassword(matcher.group("username")));
        } else if ((matcher = LoginMenuCommand.GoMenu.getMatcher(input)) != null) {
            System.out.println(LoginMenuController.goMenu(matcher.group("menu")));
        } else if (LoginMenuCommand.CurrentMenu.getMatcher(input) != null) {
            System.out.println(LoginMenuController.currentMenu());
        } else if (LoginMenuCommand.Exit.getMatcher(input) != null) {
            System.out.println(LoginMenuController.exitMenu());
        } else {
            System.out.println("invalid command");
        }
    }
}
