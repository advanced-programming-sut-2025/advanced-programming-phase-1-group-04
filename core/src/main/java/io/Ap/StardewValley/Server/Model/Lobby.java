package io.Ap.StardewValley.Server.Model;

import io.Ap.StardewValley.Common.Model.Game;
import io.Ap.StardewValley.Common.Model.Player.Player;
import io.Ap.StardewValley.Common.Model.User;

import java.util.ArrayList;

public class Lobby {
    private User host;
    private ArrayList<User> users = new ArrayList<>();
    private Player hostPlayer = null;
    private ArrayList<Player> players = new ArrayList<>();
    private String name;
    private int ID;
    private boolean isVisible;
    private boolean isPrivate;
    private String password;
    private final long time;
    private Game game;
    private int[] farmSelections = new int[4];


    public Lobby(User host, String name, int ID, boolean isVisible, boolean isPrivate, String password) {
        this.host = host;
        this.name = name;
        this.ID = ID;
        this.isVisible = isVisible;
        this.isPrivate = isPrivate;
        this.password = password;
        this.time = System.currentTimeMillis();
        users.add(host);

    }

    public User getHost() {
        return host;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTime() {
        return time;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getHostPlayer() {
        return hostPlayer;
    }

    public void setHostPlayer(Player hostPlayer) {
        this.hostPlayer = hostPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int[] getFarmSelections() {
        return farmSelections;
    }

    public void setFarmSelections(int index, int selection) {
        farmSelections[index] = selection;
    }

    public boolean isExpired() {
        return (users.size() == 1) && ((System.currentTimeMillis() - time) > 5 * 60 * 1000);
    }

    public void setHost(User host) {
        this.host = host;
    }
}
