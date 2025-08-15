package io.Ap.StardewValley.Server.App;

import io.Ap.StardewValley.Common.Message;
import io.Ap.StardewValley.Server.Controller.ServerLobbyController;

public class ServerConnectionController {
    public static Message handleCommand(Message message) {
        String controllerName;

        if ((controllerName = message.getFromBody("controller")) == null) {
            return null;
        } else {
            System.out.println("controller is hereee!");
            if (controllerName.equalsIgnoreCase("LobbyController")) {
                return ServerLobbyController.handleCommand(message);
            }
        }
        return null; //TODO
    }

    public static Message handleUpdate () {
        return null; //TODO
    }
}
