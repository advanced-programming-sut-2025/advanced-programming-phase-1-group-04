package io.Ap.StardewValley.Client.Controller.NetworkControllers;

import io.Ap.StardewValley.Client.Controller.GameMenuController;
import io.Ap.StardewValley.Client.Screen.GameScreen;
import io.Ap.StardewValley.Common.JSONUtils;
import io.Ap.StardewValley.Common.Message;
import io.Ap.StardewValley.Common.Model.App;
import io.Ap.StardewValley.Common.Model.Game;
import io.Ap.StardewValley.Common.Model.Player.Player;
import io.Ap.StardewValley.Common.Model.User;
import io.Ap.StardewValley.Server.Model.Lobby;
import io.Ap.StardewValley.StardewValley;

public class UpdateController {

    public static void startGame(Message message) {
        try {
            System.out.println("s2");
            Player currentPlayer = null;
            Player mainPlayer;
            System.out.println("s4");
            String jsonLobby = message.getFromBody("startGame");
            System.out.println("JSON Received: " + jsonLobby);
            Lobby lobby = JSONUtils.lobbyFromJson(message.getFromBody("startGame"));
            System.out.println("s5");
            if (lobby == null) return;
            System.out.println("s6");
            for (int i = 0; i < lobby.getUsers().size(); i++) {
                System.out.println("s7");
                GameMenuController.farmSelections[i] = lobby.getFarmSelections()[i];
                System.out.println("s8");
            }
            System.out.println("s9");

            mainPlayer = lobby.getHostPlayer();
            System.out.println("s10");
            if (lobby.getHost().getId() == App.getCurrentUser().getId()) {
                System.out.println("s11");
                currentPlayer = lobby.getHostPlayer();
                System.out.println("s12");
            } else {
                System.out.println("s13");
                for (Player player : lobby.getPlayers()) {
                    System.out.println("s14");
                    if (player.getId() == App.getCurrentUser().getId()) {
                        System.out.println("s15");
                        currentPlayer = player;
                        System.out.println("s16");
                    }
                }
            }
            System.out.println("s17");
            if (currentPlayer == null) return;
            System.out.println("s18");
            App.setGame(new Game(lobby.getPlayers(), currentPlayer, mainPlayer));
            System.out.println("s19");
            GameMenuController.loadNewGame();
            System.out.println("s20");
            StardewValley.getGame().setScreen(new GameScreen(GameMenuController.farmSelections));
            System.out.println("s21");
        } catch (Exception e) {
            System.out.println("s3");
        }

    }
}
