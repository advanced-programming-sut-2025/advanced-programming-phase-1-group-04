package Controller.SirkBozorg;

import Model.App;
import Model.Map.Item;
import Model.NPC.NPC;
import Model.NPC.NPCType;
import Model.NPC.Quest;
import Model.Player.AiChat;
import Model.Player.Player;
import Model.Result;
import Model.Time.Season;

public class npcController {

    public static Result meetNPC(String npcName, String message) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        boolean found = false;
        NPC target = null;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getName().equals(npcName)) {
                found = true;
                target = npc;
                break;
            }
        }
        if (!found) {
            return new Result(false, "NPC nadarim.");
        }
//        if ((Math.abs(target.getCoordinate().getX() - currentPlayer.getCoordinate().getX()) > 1)
//                || (Math.abs(target.getCoordinate().getY() - currentPlayer.getCoordinate().getY()) > 1)) {
//            return new Result(false, "NPC doore chaghi.");
//        }
        String response = AiChat.getNpcDialogue(message , target.getContext(App.getCurrentGame()));
        return new Result(true, response);
    }

    public static Result giftNPC(String npcName, String item) {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        boolean found = false;
        NPC target = null;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            if (npc.getName().equals(npcName)) {
                found = true;
                target = npc;
                break;
            }
        }
        if (!found) {
            return new Result(false, "nadarim hamchin khario.");
        }
        if ((Math.abs(target.getCoordinate().getX() - currentPlayer.getCoordinate().getX()) > 1)
                || (Math.abs(target.getCoordinate().getY() - currentPlayer.getCoordinate().getY()) > 1)) {
            return new Result(false, "NPC doore chaghi.");
        }
        if (!currentPlayer.getInventory().hasItemWithNumber(item, 1)) {
            return new Result(false, "chaghi nadari in itemo.");
        }
        Item sentItem = currentPlayer.getInventory().hasItemWithName(item);
        currentPlayer.removeItemFromInventory(item, 1);
        target.addReceivedItems(sentItem);
        return new Result(true, "delivered gift successfully.");
    }

    public static Result friendshipNPCList() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        StringBuilder builder = new StringBuilder();
        builder.append("NPC list:\n\n");
        int counter = 0;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            counter++;
            builder.append("\t").append(counter).append("- ").append(npc.getName()).append(":\n");
            builder.append("\t\t").append("Xp: ").append(npc.getFriendXp(currentPlayer)).append("\n");
            builder.append("\t\t").append("Level: ").append(npc.getFriendXp(currentPlayer) / 200).append("\n\n");
        }
        return new Result(true, builder.toString());
    }

    public static Result questList() {
        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        StringBuilder builder = new StringBuilder();
        builder.append("Quest list:\n\n");
        int counter = 0;
        for (NPC npc : App.getCurrentGame().getNPCs()) {
            for (Quest quest : npc.getQuests()) {
                if (quest.getQuestLevel() == 1) {
                    counter++;
                    builder.append(quest.toString(counter)).append("\n");
                }
                if (quest.getQuestLevel() == 2) {
                    if (npc.getLevel(currentPlayer) > 0) {
                        counter++;
                        builder.append(quest.toString(counter)).append("\n");
                    }
                }
                if (quest.getQuestLevel() == 3) {
                    if (npc.getName().equals("Ali") || npc.getName().equals("Parastoo")) {
                        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Spring) {
                            counter++;
                            builder.append(quest.toString(counter)).append("\n");
                        }
                    }
                    if (npc.getName().equals("Negar")) {
                        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Summer) {
                            counter++;
                            builder.append(quest.toString(counter)).append("\n");
                        }
                    }
                    if (npc.getName().equals("Farshad")) {
                        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Fall) {
                            counter++;
                            builder.append(quest.toString(counter)).append("\n");
                        }
                    }
                    if (npc.getName().equals("Milad")) {
                        if (App.getCurrentGame().getCurrentTime().getSeason() == Season.Winter) {
                            counter++;
                            builder.append(quest.toString(counter)).append("\n");
                        }
                    }
                }
            }
        }
        return new Result(true, builder.toString());
    }
}
