//package io.Ap.StardewValley.Server.Controller;
//
//import io.Ap.StardewValley.Client.App.ClientApp;
//import io.Ap.StardewValley.Common.Message;
//import io.Ap.StardewValley.Common.Model.Result;
//import io.Ap.StardewValley.Server.App.ClientConnectionThread;
//import io.Ap.StardewValley.Server.App.ServerApp;
//
//import java.util.HashMap;
//
//public class ServerUpdateController {
//
//    public void updateOnlineUsernames () {
//        HashMap<String, Object> body = new HashMap<>();
//        body.put("controller", "LobbyController");
//        body.put("request", "getUsername");
//
//        for (ClientConnectionThread t : ServerApp.getConnections()) {
//            Result result = receiveAnswer(new Message(body, Message.Type.command), t).getResult();
//            if (result == null) {
//
//            }
//        }
//    }
//
//    public static Message receiveAnswer (Message message, ClientConnectionThread thread) {
//        return thread.sendAndWaitForResponse(message, ServerApp.TIMEOUT_MILLIS);
//    }
//}
