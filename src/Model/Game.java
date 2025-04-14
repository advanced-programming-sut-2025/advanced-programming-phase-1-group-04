package Model;

import Model.User.User;

import java.util.ArrayList;

public class Game {
    private Date currentDate;
    private final ArrayList<User> users;
    private final User mainUser;

    public Game(ArrayList<User> users, User mainUser) {
        this.users = users;
        this.mainUser = mainUser;
    }
}
