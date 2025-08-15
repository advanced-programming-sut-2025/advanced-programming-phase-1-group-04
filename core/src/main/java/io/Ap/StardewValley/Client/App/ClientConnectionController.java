package io.Ap.StardewValley.Client.App;

import io.Ap.StardewValley.Common.Message;

import java.util.HashMap;
import java.util.Map;

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

    public static Message handleUpdate () {
        return null; //TODO
    }

}
