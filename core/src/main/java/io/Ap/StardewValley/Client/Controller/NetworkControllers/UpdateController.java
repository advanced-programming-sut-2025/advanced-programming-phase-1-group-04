package io.Ap.StardewValley.Client.Controller.NetworkControllers;

import io.Ap.StardewValley.Client.Controller.GameMenuController;
import io.Ap.StardewValley.Client.Screen.GameScreen;
import io.Ap.StardewValley.Common.JSONUtils;
import io.Ap.StardewValley.Common.Message;
import io.Ap.StardewValley.Common.Model.App;
import io.Ap.StardewValley.Common.Model.Game;
import io.Ap.StardewValley.Common.Model.Player.Player;
import io.Ap.StardewValley.Server.Model.LobbyData;
import io.Ap.StardewValley.StardewValley;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateController {

    public static void startGame(Message message) {
        try {
            System.out.println("s2");
            Player currentPlayer = null;
            Player mainPlayer;
            ArrayList<Player> players = new ArrayList<>();            System.out.println("s4");
            String jsonLobby = message.getFromBody("startGame");
            System.out.println("JSON Received: " + jsonLobby);


            LobbyData lobbyData = JSONUtils.lobbyDataFromJson(message.getFromBody("startGame"));


            System.out.println("s5");
            if (lobbyData == null) return;
            System.out.println("s6");
            for (int i = 0; i < lobbyData.getNumberOfPlayers(); i++) {
                System.out.println("s7");
                GameMenuController.farmSelections[i] = lobbyData.getFarmSelections()[i];
                System.out.println("s8");
            }
            System.out.println("s9");

            mainPlayer = getPlayer(lobbyData.getHostInfo());
            players.add(mainPlayer);

            System.out.println("s10");
            if (mainPlayer.getId() == App.getCurrentUser().getId()) {
                System.out.println("s11");
                currentPlayer = mainPlayer;
                System.out.println("s12");
            } else {
                System.out.println("s13");
                for (HashMap<String, Object> info : lobbyData.getPlayerInfo()) {
                    System.out.println("s14");
                    if ((getPlayer(info).getId()) == App.getCurrentUser().getId()) {
                        System.out.println("s15");
                        currentPlayer = getPlayer(info);
                        System.out.println("s16");
                    }
                    players.add(getPlayer(info));
                }
            }
            System.out.println("s17");
            if (currentPlayer == null) return;
            System.out.println("s18");
            App.setGame(new Game(players, currentPlayer, mainPlayer));
            System.out.println("s19");
            GameMenuController.loadNewGame();
            System.out.println("s20");
//            StardewValley.getGame().setScreen(new GameScreen(GameMenuController.farmSelections));
            System.out.println("s21");
        } catch (Exception e) {
            System.out.println("s3");
        }
    }

    public static Player getPlayer(HashMap<String, Object> info) {
        String hairColor = (String) info.get("hairColor");
        String pantColor = (String) info.get("pantColor");
        int pantIndex = Integer.parseInt((String) info.get("pantIndex"));
        int shirtIndex = Integer.parseInt((String) info.get("shirtIndex"));
        int hairIndex = Integer.parseInt((String) info.get("hairIndex"));
        int id = Integer.parseInt((String) info.get("id"));
        int farm = Integer.parseInt((String) info.get("farm"));

        return new Player(hairColor, pantColor, pantIndex, shirtIndex, hairIndex, id, farm);
    }

}
