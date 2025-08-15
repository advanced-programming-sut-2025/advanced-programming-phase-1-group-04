package io.Ap.StardewValley.Client.App;

import io.Ap.StardewValley.Client.Controller.NetworkControllers.UpdateController;
import io.Ap.StardewValley.Common.Message;


public class ClientConnectionController {
    public static Message handleCommand(Message message) {
        if (message.getFromBody("command").equals("status")) {
//            return status();
        }
        else {
            throw new UnsupportedOperationException("wrong command");
        }
        return null; //TODO
    }

    public static void handleUpdate (Message message) {
        System.out.println("s1");
        if (message.getFromBody("startGame") != null) {
            System.out.println("s");
            UpdateController.startGame(message);
            return;
        }
    }

}