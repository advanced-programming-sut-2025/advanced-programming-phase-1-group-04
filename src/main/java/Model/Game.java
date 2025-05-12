package Model;

import Model.Map.GameMap;
import Model.Player.Player;
import Model.Time.Season;
import Model.Time.Weather;
import Model.Time.WeekDay;
import Model.Time.DateAndTime;

import java.util.ArrayList;
import java.util.Map;

public class Game {
    private final ArrayList<Player> players;
    private Player mainPlayer;
    private Player currentPlayer;

    private GameMap map;

    private DateAndTime currentTime = new DateAndTime (9, 1, WeekDay.Monday, Season.Spring);
    private Weather currentWeather;
    private Weather tomorrowWeather;

    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.players = players;
        this.mainPlayer = currentPlayer;
        this.currentPlayer = currentPlayer;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setMainPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public DateAndTime getCurrentTime() {
        return this.currentTime;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameMap getMap() {
        return map;
    }

    public static Player getCurrentPlayer() {
        return Game.currentPlayer;
    }

}
