package Model;

import java.util.ArrayList;

public class Game {
    public static DateAndTime currentTime;
    public static ArrayList<User> users;
    public static User mainUser;

    public Game(ArrayList<User> users, User mainUser) {
        this.users = users;
        this.mainUser = mainUser;
    }
}