package Model;

import Model.Command.Menu;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static Menu currentMenu = Menu.LoginMenu;
    private static User currentUser = null;
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
        return 0;
    }
}
