package Model;

import Model.Enum.Menu;
import Model.Enum.Season;
import Model.Enum.WeekDay;

public class App {
    public static Menu currentMenu = Menu.LoginMenu;
    public static Game currentGame = null;
    public static DateAndTime currentTime = new DateAndTime (9, 1, WeekDay.Monday, Season.Spring);


}
