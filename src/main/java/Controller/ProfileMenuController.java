package Controller;

import Model.App;
import Model.Command.LoginMenuCommand;
import Model.Command.Menu;
import Model.Result;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileMenuController {
    public static Result changeUsername (String username) throws IOException {
        String oldUsername = App.getCurrentUser().getUsername();
        if (!LoginMenuCommand.Name.isMatch(username)) {
            return new Result(false, "Username format is invalid!\nUsernames can only contain lowercase and uppercase letters, numbers, and the _ symbol.");
        } else if (oldUsername.equals(username)) {
            return new Result(false, "Old and new usernames are the same!");
        }  else if (App.getUserByUsername(username) != null) {
            return new Result(false, "Username already exist!");
        }

        App.getCurrentUser().setUsername(username);

        Gson gson = new Gson();
        File oldFile = new File("users/" + oldUsername + ".json");
        File newFile = new File("users/" + username + ".json");
        FileWriter writer = new FileWriter(oldFile);

        gson.toJson(App.getCurrentUser(), writer);
        writer.close();
        oldFile.renameTo(newFile);

        return new Result(true, "Username successfully changed.");
    }

    public static Result changeNickname (String nickname) throws IOException {
        if (!LoginMenuCommand.Name.isMatch(nickname)) {
            return new Result(false, "Nickname format is invalid!\nNickname can only contain lowercase and uppercase letters, numbers, and the _ symbol.");
        } else if (App.getCurrentUser().getNickname().equals(nickname)) {
            return new Result(false, "Old and new nickname are the same!");
        }

        App.getCurrentUser().setNickname(nickname);
        updateCurrentUser();
        return new Result(true, "Nickname successfully changed.");
    }

    public static Result changeEmail (String email) throws IOException {
        if (!LoginMenuCommand.Email.isMatch(email)) {
            return new Result(false, "Email format is invalid!");
        } else if (App.getCurrentUser().getEmail().equals(email)) {
            return new Result(false, "Old and new email are the same!");
        }

        App.getCurrentUser().setEmail(email);
        updateCurrentUser();
        return new Result(true, "Email successfully changed.");
    }

    public static Result changePassword (String newPassword, String oldPassword) throws IOException {
        if (!LoginMenuCommand.Password.isMatch(newPassword)) {
            return new Result(false, "Password format is invalid!");
        } else if (newPassword.length() < 8) {
            return new Result(false, "Your password is not strong enough!\nIt must be at least 8 characters long.");
        } else if (!newPassword.matches(".*[a-z].*")) {
            return new Result(false, "Your password is not strong enough!\nIt must contain lowercase letters.");
        } else if (!newPassword.matches(".*[A-Z].*")) {
            return new Result(false, "Your password is not strong enough!\nIt must contain uppercase letters.");
        } else if (!newPassword.matches(".*[0-9].*")) {
            return new Result(false, "Your password is not strong enough!\nIt must contain numbers.");
        } else if (!newPassword.matches(".*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/|\\\\].*")) {
            return new Result(false, "Your password is not strong enough!\nIt must contain special symbols.");
        } else if (!App.getCurrentUser().getPassword().equals(LoginMenuController.getHashPassword(oldPassword))) {
            return new Result(false, "Old password entered is incorrect!");
        } else if (App.getCurrentUser().getPassword().equals(LoginMenuController.getHashPassword(newPassword))) {
            return new Result(false, "Old and new password are the same!");
        }

        App.getCurrentUser().setPassword(LoginMenuController.getHashPassword(newPassword));
        updateCurrentUser();
        return new Result(true, "Password successfully changed.");
    }

    public static Result showInfo() {
        return new Result(true, App.getUserByUsername(App.getCurrentUser().getUsername()).toString());
    }

    public static Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in profile menu");
        }

        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in profile menu");
    }

    public static Result exitMenu () {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in main menu");
    }

    private static void updateCurrentUser () throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/" + App.getCurrentUser().getUsername() + ".json");
        gson.toJson(App.getCurrentUser(), writer);
        writer.close();
    }
}
