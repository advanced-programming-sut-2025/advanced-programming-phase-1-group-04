package Model.Player;

import Model.Map.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private HashMap<Item, Integer> items = new HashMap<>();
    private int capacity;
    private int trashCanLevel;

    public Inventory (int capacity, int trashCanLevel) {
        this.capacity = capacity;
        this.trashCanLevel = trashCanLevel;
    }
    //TODO: we have maxint as infinite capacity

    public boolean addItem (Item item, int quantity) {
        if (items.containsKey(item)) {
            int newQuantity = items.get(item) + quantity;
            items.put(item, newQuantity);
            return true;
        }
        if (items.size() < capacity) {
            items.put(item, quantity);
            return true;
        }
        return false;
    }

    public boolean removeItem (String itemName, int quantity) {
        Item item;
        if ((item = hasItemWithName(itemName)) == null) {
            return false;
        }
        if (items.get(item) < quantity) {
            return false;
        }
        if (items.get(item) == quantity || quantity == -1) {
            items.remove(item);
            return true;
        }
        int newQuantity = items.get(item) - quantity;
        items.put(item, newQuantity);
        return true;
    }

    public void setCapacity (int capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public int getTrashCanLevel() {
        return trashCanLevel;
    }

    public void setTrashCanLevel(int trashCanLevel) {
        this.trashCanLevel = trashCanLevel;
    }

    public String getTrashCanType() {
        switch (trashCanLevel) {
            case 2:
                return "copper";
            case 3:
                return "steel";
            case 4:
                return "gold";
            case 5:
                return "iridium";
            default:
                return "starter";
        }
    }

    public Item hasItemWithName(String itemName) {
        itemName = itemName.toLowerCase();
        for (Item item : items.keySet()) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

}
