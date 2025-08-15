package io.Ap.StardewValley.Client.App;

import io.Ap.StardewValley.Client.Controller.NetworkControllers.ClientLobbyController;
import io.Ap.StardewValley.Client.Controller.NetworkControllers.UpdateController;
import io.Ap.StardewValley.Common.Message;
import io.Ap.StardewValley.Server.Controller.ServerLobbyController;


public class ClientConnectionController {
    public static Message handleCommand(Message message) {
        String controllerName;

        if ((controllerName = message.getFromBody("controller")) == null) {
            return null;
        } else {
            System.out.println("controller is hereee!");
            if (controllerName.equalsIgnoreCase("LobbyController")) {
                return ClientLobbyController.handleCommand(message);
            }
        }
        return null;
    }

    public static void handleUpdate (Message message) {

        if (message.getFromBody("startGame") != null) {
            UpdateController.startGame(message);
            return;
        }
//        if (message.getFromBody("onlineUsers") != null) {
//            UpdateController.updateOnlineUsers(message);
//        }

    }

}