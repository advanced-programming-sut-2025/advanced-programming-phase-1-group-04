package Model;

import Model.Time.DateAndTime;
import Model.Time.Season;
import Model.Time.WeekDay;

import java.util.ArrayList;

public class Game {
    public static ArrayList<User> users;
    public static User mainUser;
    private static DateAndTime currentTime = new DateAndTime (9, 1, WeekDay.Monday, Season.Spring);

    public Game(ArrayList<User> users, User mainUser) {
        this.users = users;
        this.mainUser = mainUser;
    }

    public static DateAndTime getCurrentTime() {
        return currentTime;
    }
}
