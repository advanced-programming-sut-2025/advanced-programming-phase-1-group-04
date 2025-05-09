package Model.Player;

import Model.Map.Item;

import java.util.HashMap;

public class Inventory {
    private HashMap<Item, Integer> items = new HashMap<>();
    private double capacity;

    public Inventory (double capacity) {
        this.capacity = capacity;
    }
    //TODO: we have infinity as capacity

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

    public boolean removeItem (Item item, int quantity) {
        if (!items.containsKey(item)) {
            return false;
        }
        if (items.get(item) < quantity) {
            return false;
        }
        if (items.get(item) == quantity) {
            items.remove(item);
            return true;
        }
        int newQuantity = items.get(item) - quantity;
        items.put(item, newQuantity);
        return true;
    }

    public void setCapacity (double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }
}
