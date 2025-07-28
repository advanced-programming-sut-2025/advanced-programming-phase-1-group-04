package io.Ap.StardewValley.Model;

import com.badlogic.gdx.graphics.Color;
import io.Ap.StardewValley.Model.Command.Menu;
import com.google.gson.Gson;
import io.Ap.StardewValley.Screen.MenuScreen.MainMenuScreen;
import io.Ap.StardewValley.Screen.MenuScreen.StartMenuScreen;
import io.Ap.StardewValley.StardewValley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static Menu currentMenu;
    private static User currentUser;
    private static Game game = null;
    private static final KeyManager keyManager = new KeyManager();

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        App.game = game;
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

    public static void loadAppScreen() throws FileNotFoundException {
        File file = new File("users/loggedIn.json");

        FileReader reader = new FileReader(file);
        Gson gson = new Gson();
        User user = gson.fromJson(reader, User.class);
        if (user == null) {
            App.setCurrentMenu(Menu.LoginMenu);
            StardewValley.getGame().setScreen(new StartMenuScreen());
            App.setCurrentUser(null);
        } else {
            App.setCurrentMenu(Menu.MainMenu);
            StardewValley.getGame().setScreen(new MainMenuScreen());
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
        if (username == null) return null;
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

    public static KeyManager getKeyManager() {
        return keyManager;
    }

    public static Color getColor(String hairColor) {
        return switch (hairColor) {
            case "Black" ->  new Color(0x2c2c2dff);
            case "Brown" -> new Color(0x91513bff);
            case "Blonde" -> new Color(0.98f, 0.94f, 0.55f, 1f);
            case "Red" -> new Color(0x8e1f0cff);
            case "Blue" -> new Color(0x2121a3ff);
            case "Cyan" -> Color.CYAN;
            case "Green" -> new Color(0x277f2bff);
            case "Magenta" -> new Color(0x9e59dbff);
            case "Orange" -> Color.ORANGE;
            case "Pink" -> Color.PINK;
            case "Yellow" -> new Color(0xffee2dff);
            case "Gray" -> Color.GRAY;
            case "White" -> Color.WHITE;
            default -> Color.WHITE;
        };
    }
}
