package Model;

import Model.Map.Coordinate;
import Model.Map.GameMap;
import Model.Map.Region;
import Model.Map.Tile;
import Model.Player.Player;
import Model.Shop.Shop;
import Model.Shop.ShopType;
import Model.Time.Season;
import Model.Time.Weather;
import Model.Time.WeekDay;
import Model.Time.DateAndTime;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final ArrayList<Player> players;
    private Player mainPlayer;
    private Player currentPlayer;

    private GameMap map;
    //private Region currentRegion = null;


    private DateAndTime currentTime = new DateAndTime (9, 1, Weather.Sunny);
    private Weather tomorrowWeather;


    private ArrayList<Shop> shops = new ArrayList<>();

    public Game(ArrayList<Player> players, Player currentPlayer) {
        this.players = players;
        this.mainPlayer = currentPlayer;
        this.currentPlayer = currentPlayer;

        this.tomorrowWeather = Weather.Sunny;
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

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
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

    public Tile getTile(Coordinate coordinate) {
        Tile[][] tiles = this.getMap().getFullMap();
        return tiles[coordinate.getX()][coordinate.getY()];
    }

    public Shop getShop(ShopType type) {
        for (Shop s : shops) {
            if (s.getType() == type) {
                return s;
            }
        }
        return shops.get(0);
    }

    public ArrayList<Shop> getShops () {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = new ArrayList<>(shops);
    }

}
