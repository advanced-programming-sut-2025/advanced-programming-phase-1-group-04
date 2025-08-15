package io.Ap.StardewValley.Client.Controller.NetworkControllers;

import io.Ap.StardewValley.Client.App.ClientApp;
import io.Ap.StardewValley.Common.Message;
import io.Ap.StardewValley.Common.Model.App;
import io.Ap.StardewValley.Common.Model.Result;

import java.util.HashMap;

public class ClientLobbyController {
    public static Result makeLobby (String name, String password, boolean isVisible, boolean isPrivate) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "makeLobby");
        body.put("name", name);
        body.put("password", password);
        if (isVisible) {
            body.put("isVisible", "true");
        } else {
            body.put("isVisible", "false");
        }
        if (isPrivate) {
            body.put("isPrivate", "true");
        } else {
            body.put("isPrivate", "false");
        }

        body.put("host", App.getCurrentUser().getUsername());

        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result getLobbyList () {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "getLobbyList");
        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result joinLobby (String name, String password) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "joinLobby");
        body.put("name", name);
        body.put("password", password);

        body.put("user", App.getCurrentUser().getUsername());

        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result setHostPlayer (String lobbyName, String hairColor, String pantColor, int pantIndex, int shirtIndex, int hairIndex, int farmIdSelect) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "setHostPlayer");
        body.put("name", lobbyName);
        body.put("hairColor", hairColor);
        body.put("pantColor", pantColor);
        body.put("pantIndex", "" + pantIndex);
        body.put("shirtIndex", "" + shirtIndex);
        body.put("hairIndex", "" + hairIndex);
        body.put("id", "" + App.getCurrentUser().getId());
        body.put("farmIdSelect", "" + farmIdSelect);

        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result addPlayer (String lobbyName, String hairColor, String pantColor, int pantIndex , int shirtIndex, int hairIndex, int farmIdSelect) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "addPlayer");
        body.put("name", lobbyName);
        body.put("hairColor", hairColor);
        body.put("pantColor", pantColor);
        body.put("pantIndex", "" + pantIndex);
        body.put("shirtIndex", "" + shirtIndex);
        body.put("hairIndex", "" + hairIndex);
        body.put("id", "" + App.getCurrentUser().getId());
        body.put("farmIdSelect", "" + farmIdSelect);

        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result getPlayersList (String lobbyName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "getPlayersList");
        body.put("name", lobbyName);
        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result leaveLobby (String lobbyName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "leaveLobby");
        body.put("name", lobbyName);
        body.put("username", App.getCurrentUser().getUsername());
        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result hostLeaveLobby (String lobbyName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "hostLeaveLobby");
        body.put("name", lobbyName);
        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Result startGame (String lobbyName) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("controller", "LobbyController");
        body.put("request", "startGame");
        body.put("name", lobbyName);
        return receiveAnswer(new Message(body, Message.Type.command)).getResult();
    }

    public static Message receiveAnswer (Message message) {
        return ClientApp.getServerConnectionThread().sendAndWaitForResponse(message, ClientApp.TIMEOUT_MILLIS);
    }
}
