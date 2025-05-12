package Model;

import Model.Player.Player;
import Model.Time.Season;
import Model.Time.WeekDay;
import Model.Time.DateAndTime;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Player> players;
    private static Player mainPlayer;
    private static Player currentPlayer;
    private static DateAndTime currentTime = new DateAndTime (9, 1, WeekDay.Monday, Season.Spring);

    public Game(ArrayList<Player> players, Player player) {
        Game.players =  players;
        Game.mainPlayer = player;
    }

    public static DateAndTime getCurrentTime() {
        return currentTime;
    }

    public static Player getCurrentPlayer() {
        return Game.currentPlayer;
    }

}
