package Controller;

import Model.App;
import Model.Command.LoginMenuCommand;
import Model.Command.Menu;
import Model.Enum.SecurityQuestion;
import Model.Result;
import Model.User;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoginMenuController {
    public static Result register(String username, String password, String rePassword, String nickName, String email, String gender) throws IOException {
        if (!LoginMenuCommand.Name.isMatch(username)) {
            return new Result(false, "Username format is invalid!\nUsernames can only contain lowercase and uppercase letters, numbers, and the _ symbol.");
        } else if (!LoginMenuCommand.Name.isMatch(nickName)) {
            return new Result(false, "Nickname format is invalid!\nNickname can only contain lowercase and uppercase letters, numbers, and the _ symbol.");
        } else if (App.getUserByUsername(username) != null) {
            return new Result(false, "Username already exist!");
        } else if (!LoginMenuCommand.Email.isMatch(email)) {
            return new Result(false, "Email format is invalid!");
        } else if (!LoginMenuCommand.Gender.isMatch(gender)) {
            return new Result(false, "Gender format is invalid!\nUse male or female for your gender!");
        } else if (!password.equals("random")) {
            if (!LoginMenuCommand.Password.isMatch(password)) {
                return new Result(false, "Password format is invalid!");
            } else if (password.length() < 8) {
                return new Result(false, "Your password is not strong enough!\nIt must be at least 8 characters long.");
            } else if (!password.matches(".*[a-z].*")) {
                return new Result(false, password + "Your password is not strong enough!\nIt must contain lowercase letters.");
            } else if (!password.matches(".*[A-Z].*")) {
                return new Result(false, password + "Your password is not strong enough!\nIt must contain uppercase letters.");
            } else if (!password.matches(".*[0-9].*")) {
                return new Result(false, "Your password is not strong enough!\nIt must contain numbers.");
            } else if (!password.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/|\\\\].*")) {
                return new Result(false, "Your password is not strong enough!\nIt must contain special symbols.");
            } else if (!password.equals(rePassword)) {
                return new Result(false, "RePassword isn't match with password!");
            }
        } else if (password.equals("random")) {
            String randomPassword = generatePassword();
            return new Result(false, "Generated password: " + randomPassword + "\nDo you accept this password? (yes/no)");
        }

        User user = new User(username, password, nickName, email, gender);
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/" + username + ".json");
        gson.toJson(user, writer);
        writer.close();
        return new Result(true,"Now pick a security question:\n" + SecurityQuestion.getQuestions());
    }

    public static Result securityQuestion(String username, int number, String answer, String reAnswer) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(true, "Something went wrong. Register again!");
        } else if (number < 1 || number > 10) {
            return new Result(false, "Pick a question number between 1-10!");
        } else if (!answer.equals(reAnswer)) {
            return new Result(false, "reAnswer isn't match with answer!");
        }

        user.setQuestion(SecurityQuestion.valueOf("S" + number));
        user.setAnswer(answer);
        return new Result(true, "User registered successfully.");
    }

    public static Result login(String username, String password, String loggedIn) {

        return new Result(true, "");
    }

    public static Result forgetPassword(String username) {

        return new Result(true, "");
    }

    public static Result securityAnswer(String answer) {

        return new Result(true, "");
    }

    public static Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in login menu");
        }

        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in login menu");
    }

    public static Result exitMenu () {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Bye bye");
    }

    private static String generatePassword() {
        //TODO
        return "";
    }
}