package Controller;

import Model.App;
import Model.Command.LoginMenuCommand;
import Model.Command.Menu;
import Model.Command.SecurityQuestion;
import Model.Result;
import Model.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                return new Result(false, "Your password is not strong enough!\nIt must contain lowercase letters.");
            } else if (!password.matches(".*[A-Z].*")) {
                return new Result(false, "Your password is not strong enough!\nIt must contain uppercase letters.");
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

        User user = new User(username, getHashPassword(password), nickName, email, gender);
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/" + username + ".json");
        gson.toJson(user, writer);
        writer.close();
        return new Result(true,"Benazam.\nNow pick a security question:\n" + SecurityQuestion.getQuestions());
    }

    public static Result securityQuestion(String username, int number, String answer, String reAnswer) throws IOException {
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

        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/" + username + ".json");
        gson.toJson(user, writer);
        writer.close();
        return new Result(true, "User registered successfully.");
    }

    public static Result login(String username, String password, String loggedIn) throws IOException {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "There is no such username!");
        } else if (!getHashPassword(password).equals(user.getPassword())) {
            return new Result(false, "Password is incorrect!");
        }

        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/loggedIn.json");
        if (loggedIn == null) gson.toJson(null, writer);
        else gson.toJson(user, writer);
        writer.close();

        App.setCurrentUser(user);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "You login successfully. Now you are in Main menu.");
    }


    public static Result forgetPassword(String username) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "There is no such username!");
        }

        return new Result(true, "Now answer the security question:\n" + user.getSecurityQuestion());
    }

    public static Result forgetPassword(String username, String password) throws IOException {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "There is no such username!");
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
            } else if (getHashPassword(password).equals(user.getPassword())) {
                return new Result(false, "New and old passwords are the same!");
            }
        } else if (password.equals("random")) {
            String randomPassword = generatePassword();
            return new Result(false, "Generated password: " + randomPassword + "\nDo you accept this password? (yes/no)");
        }

        user.setPassword(getHashPassword(password));
        App.setCurrentUser(user);

        Gson gson = new Gson();
        FileWriter writer = new FileWriter("users/" + username + ".json");
        gson.toJson(user, writer);
        writer.close();

        return new Result(true, "Your password changed successfully!");
    }

    public static Result securityAnswer(String username, String answer) {
        if (!App.getUserByUsername(username).getAnswer().equals(answer)) {
            return new Result(false, "answer is incorrect!");
        }

        return new Result(true, "Benazam. Now enter new password:");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in login menu");
    }

    public static Result exitMenu () {
        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, "Bye bye");
    }

    private static String generatePassword() {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = "abcdefghijklmnopqrstuvwxyz";
        final String digit = "0123456789";
        final String symbol = "!@#$%^&*()_+-={}[]:;\"'<>,.?/|\\";

        SecureRandom random = new SecureRandom();
        int length = random.nextInt(10) + 8;

        List<Character> list = new ArrayList<>();
        list.add(upper.charAt(random.nextInt(upper.length())));
        list.add(lower.charAt(random.nextInt(lower.length())));
        list.add(digit.charAt(random.nextInt(digit.length())));
        list.add(symbol.charAt(random.nextInt(symbol.length())));

        String all = upper + lower + digit + symbol;
        while (list.size() < length)
            list.add(all.charAt(random.nextInt(all.length())));

        Collections.shuffle(list);

        StringBuilder password = new StringBuilder();
        for (char ch : list) {
            password.append(ch);
        }

        return password.toString();
    }

    public static String getHashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!");
        }
    }
}
