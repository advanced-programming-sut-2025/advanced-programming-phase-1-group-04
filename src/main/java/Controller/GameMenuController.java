package Controller;

import Controller.SirkBozorg.NightController;
import Model.App;
import Model.Command.Menu;
import Model.Game;
import Model.Map.Coordinate;
import Model.Map.GameMap;
import Model.Map.Tile;
import Model.Player.Player;
import Model.Result;
import Model.Shop.BlackSmith.BlackSmith;
import Model.Shop.CarpentersShop.CarpentersShop;
import Model.Shop.FishShop.FishShop;
import Model.Shop.JojaMart.JojaMart;
import Model.Shop.MarniesRanch.MarniesRanch;
import Model.Shop.PierresGeneralStore.PierresStore;
import Model.Shop.TheStardropSaloon.TheStardropSaloon;
import Model.User;

import java.util.ArrayList;
import java.util.List;

public class GameMenuController {
    private static final int[] farmSelections = new int[4];
    private static final boolean[] deleteGame = new boolean[4];

    public static Result newGame(String username1, String username2, String username3) {
        if (App.getCurrentGame() != null) {
            return new Result(false, "You are in game. Boro khodeto siah kon");
        }
        Player currentPlayer = new Player(App.getCurrentUser().getId(), 1);
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentPlayer);

        if (username1 == null) {
            return new Result(false, "You should start the game with at least one more player!");
        }

        User user1 = App.getUserByUsername(username1);
        if (user1 == null)
            return new Result(false, username1 + " not found!");
        players.add(new Player(user1.getId(), 2));

        if (username2 != null) {
            User user2 = App.getUserByUsername(username2);
            if (user2 == null)
                return new Result(false, username2 + " not found!");
            players.add(new Player(user2.getId(), 3));
        }

        if (username3 != null) {
            User user3 = App.getUserByUsername(username3);
            if (user3 == null)
                return new Result(false, username3 + " not found!");
            players.add(new Player(user3.getId(), 4));
        }

        App.setCurrentGame(new Game(players, currentPlayer));
        return new Result(true, "Game started successfully. Now you should choose your map:");
    }

    public static Result chooseMap(int i ,int farm) {
        if (farm < 1 || farm > 2) {
            return new Result(false, "Invalid number. Please enter a number between 1 and 2:");
        }

        GameMenuController.farmSelections[i] = farm;
        return new Result(true, "Benazam.");
    }

    public static Result loadNewGame() {
        for (int i = App.getCurrentGame().getPlayers().size(); i < 4; i++) {
            farmSelections[i] = 1;
        }
        App.getCurrentGame().setShops(new ArrayList<>(List.of(new BlackSmith(), new CarpentersShop(), new FishShop(),
                new JojaMart(), new MarniesRanch(), new PierresStore(), new TheStardropSaloon())));
        App.getCurrentGame().setMap(new GameMap(farmSelections));
        App.getCurrentGame().getMap().setFulMap();
        return new Result(true, "Game loaded. Now you are in the game. Boro eshgh kon.");
    }

    public static Result loadGame() {
        // TODO: save & load game
        return new Result(true, "tekh rakab");
    }

    public static Result exitGame() {
        if (App.getCurrentGame().getCurrentPlayer().getId() != App.getCurrentGame().getMainPlayer().getId()) {
            return new Result(false, "Just main player(who created the game or last loaded it) can use the following command!");
        }

        // TODO: save games
        App.setCurrentGame(null);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Game saved successfully. Now you are in Main menu");
    }

    public static Result deleteGame(int i, String vote) {
        if (!vote.equalsIgnoreCase("yes") && !vote.equalsIgnoreCase("no")) {
            return new Result(false, "Just use yes or no, stupid.");
        }

        deleteGame[i] = vote.equalsIgnoreCase("yes");
        return new Result(true, "Benazam.");
    }

    public static Result resultDeleteGame() {
        if (allPlayersVotedYes()) {
            // TODO:  check saved games in games folder and delete them
            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.MainMenu);
            return new Result(true, "Bazi hazf shod. Mobarak kheilia");
        } else {
            return new Result(false, "All players must agree to delete the game!");
        }
    }

    public static Result nextTurn() {
        //Bug: وقتی نفر اخر انرژیش صفر شه  هیچ موقع نمیره ساعت بعدی.....
        int index = App.getCurrentGame().getPlayers().indexOf(App.getCurrentGame().getCurrentPlayer());
        int totalPlayers = App.getCurrentGame().getPlayers().size();
        int nextIndex = (index + 1) % totalPlayers;
        // update time
        if (nextIndex == 0) {
            App.getCurrentGame().getCurrentTime().addHour(1);
            if (App.getCurrentGame().getCurrentTime().getHour() == 24) {
                NightController.nightControl();
                return new Result(true, "Shab bekheir...");
            }
        }

        // skip players with 0 energy:
        int startingIndex = nextIndex;
        do {
            Player candidate = App.getCurrentGame().getPlayers().get(nextIndex);
            if (candidate.getEnergy() > 0) {
                App.getCurrentGame().setCurrentPlayer(candidate);
                App.getCurrentGame().getCurrentPlayer().resetMovesThisTurn();
                return new Result(true, "Now it's " + candidate.getUsername() + "'s turn.");
            }
            nextIndex = (nextIndex + 1) % totalPlayers;
        } while (nextIndex != startingIndex);

        NightController.nightControl();
        return new Result(true, "Nobody had energy. Skipping to night.");
    }

    public static Result gotoNextDay() {
        NightController.nightControl();
        return new Result(true, "Shab bekheir...");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in game menu");
    }

    public static Result currentPlayer() {
        return new Result(true, App.getCurrentGame().getCurrentPlayer().toString());
    }

    public static Tile getTileByDirection (String direction) {
        direction = direction.toLowerCase();
        Coordinate coordinate = new Coordinate(App.getCurrentGame().getCurrentPlayer().getCoordinate().getX(),
                App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        if (coordinate == null) {
            return null;
        }
        int x = coordinate.getX();
        int y = coordinate.getY();
        switch (direction) {
            case "n":
                if (x > 0) {
                    coordinate.setX(x - 1);
                } else {return null;}
                break;
            case "s":
                if (x < 89) {
                    coordinate.setX(x + 1);
                } else {return null;}
                break;
            case "e":
                if (y < 119) {
                    coordinate.setY(y + 1);
                } else {return null;}
                break;
            case "w":
                if (y > 0) {
                    coordinate.setY(y - 1);
                } else {return null;}
                break;
            case "ne":
                if (x > 0 && y < 119) {
                    coordinate.setX(x - 1);
                    coordinate.setY(y + 1);
                } else {return null;}
                break;
            case "nw":
                if (x > 0 && y > 0) {
                    coordinate.setX(x - 1);
                    coordinate.setY(y - 1);
                } else {return null;}
                break;
            case "se":
                if (x < 89 && y < 119) {
                    coordinate.setX(x + 1);
                    coordinate.setY(y + 1);
                } else {return null;}
                break;
            case "sw":
                if (x < 89 && y > 0) {
                    coordinate.setX(x + 1);
                    coordinate.setY(y - 1);
                } else {return null;}
                break;
            default:
                return null;
        }

        return App.getCurrentGame().getTile(coordinate);
    }

    public static void moveControl () {
        App.getCurrentGame().getCurrentPlayer().addMovesThisTurn();
    }

    private static boolean allPlayersVotedYes() {
        int playerCount = App.getCurrentGame().getPlayers().size();
        for (int i = 1; i < playerCount; i++) {
            if (!deleteGame[i]) {
                return false;
            }
        }
        return true;
    }
}
