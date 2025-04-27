package Model;

import Model.Command.Menu;
import Model.Enum.Season;
import Model.Enum.WeekDay;

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

    public static DateAndTime getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime(DateAndTime currentTime) {
        App.currentTime = currentTime;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
}
