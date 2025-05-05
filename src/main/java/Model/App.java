package Model;

import Model.Command.Menu;
import com.badlogic.gdx.utils.Null;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static Menu currentMenu;
    private static User currentUser;
    private static Game currentGame = null;


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static void loadApp() throws FileNotFoundException {
        File file = new File("users/loggedIn.json");

        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        User user = gson.fromJson(reader, User.class);
        if (user == null) {
            App.setCurrentMenu(Menu.LoginMenu);
            App.setCurrentUser(null);
        } else {
            App.setCurrentMenu(Menu.MainMenu);
            App.setCurrentUser(user);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static User getUserByUsername (String username) {
        File file = new File("users/" + username + ".json");

        if (!file.exists()) return null;

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getNumberOfUsers() {
        File folder = new File("users");
        File[] files = folder.listFiles();
        int length = (files != null) ? files.length - 1: 0;
        return length;
    }
}
