package Model.NPC;

import Model.Map.Item;
import Model.Map.Place;

import java.util.ArrayList;

public class NPC {
    private String name;
    private String job;
    private Place home;
    private ArrayList<Item> favorites = new ArrayList<>();
    private ArrayList<Quest> quests = new ArrayList<>();
}
