package Controller;

import Model.App;
import Model.Command.Menu;
import Model.Game;
import Model.Map.GameMap;
import Model.Player.Player;
import Model.Result;
import Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenuController {
    private static int[] farmSelections = new int[4];
    public static Result newGame(String username1, String username2, String username3) {
        Player currentPlayer = new Player(App.getCurrentUser().getId());
        ArrayList<Player> players = new ArrayList<>();
        players.add(currentPlayer);

        if (username1 == null) {
            return new Result(false, "You should start the game with at least one more player!");
        }

        User user1 = App.getUserByUsername(username1);
        if (user1 == null)
            return new Result(false, "Username1 not found!");
        players.add(new Player(user1.getId()));

        if (username2 != null) {
            User user2 = App.getUserByUsername(username2);
            if (user2 == null)
                return new Result(false, "Username2 not found!");
            players.add(new Player(user2.getId()));
        }

        if (username3 != null) {
            User user3 = App.getUserByUsername(username3);
            if (user3 == null)
                return new Result(false, "Username3 not found!");
            players.add(new Player(user3.getId()));
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
        App.getCurrentGame().setMap(new GameMap(farmSelections));
        return new Result(true, "Game loaded. Now you are in the game. Boro eshgh kon.");
    }
    public static Result goMenu (String menu) {
        if (!menu.equals("main")) {
            return new Result(false, "You can't go to " + menu + " menu in game menu");
        }
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Now you are in " + menu + " menu");
    }

    public static Result currentMenu () {
        return new Result(true, "You are in game menu");
    }
}
