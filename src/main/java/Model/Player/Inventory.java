package Model.Player;

import Model.Map.Item;
import Model.Tool.*;

import java.util.*;

public class Inventory {

    private HashMap<String, ItemStack> items = new HashMap<>();

    private int capacity;
    private int trashCanLevel;

    public Inventory(int capacity, int trashCanLevel) {
        this.capacity = capacity;
        this.trashCanLevel = trashCanLevel;
        addItem(new Hoe(ToolLevel.Starter), 1);
        addItem(new Pickaxe(ToolLevel.Starter), 1);
        addItem(new Axe(ToolLevel.Starter), 1);
        addItem(new WateringCan(ToolLevel.Starter), 1);
        addItem(new Scythe(), 1);
        addItem(new MilkPail(), 1);
    }

    public boolean addItem(Item item, int quantity) {
        String name = item.getName().toLowerCase();
        ItemStack stack = items.getOrDefault(name, new ItemStack());

        for (int i = 0; i < quantity; i++) {
            stack.add(item);
        }

        if (!items.containsKey(name)) {
            if (items.size() >= capacity) return false;
            items.put(name, stack);
        }

        return true;
    }

    public boolean addItem(Item item) {
        String name = item.getName().toLowerCase();
        ItemStack stack = items.getOrDefault(name, new ItemStack());

            stack.add(item);

        if (!items.containsKey(name)) {
            if (items.size() >= capacity) return false;
            items.put(name, stack);
        }//TODO Aynaz

        return true;
    }


    public boolean removeItem(String itemName, int quantity) {
        String name = itemName.toLowerCase();
        ItemStack stack = items.get(name);
        if (stack == null) {
            items.remove(name);
            return false;
        }
        if (stack.getCount() < quantity) return false;

        if (quantity == -1 || quantity == stack.getCount()) {
            items.remove(name);
            return true;
        }

        for (int i = 0; i < quantity; i++) {
            if (!stack.items.isEmpty()) stack.items.remove(0);
        }
        return true;
    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<String, ItemStack> getItems() {
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
        ItemStack stack = items.get(itemName.toLowerCase());
        if (stack == null || stack.items.isEmpty()) return null;
        return stack.items.get(0);
    }

    public boolean hasItemWithNumber(String itemName, int number) {
        ItemStack stack = items.get(itemName.toLowerCase());
        return stack != null && stack.getCount() >= number;
    }

    public int getRemainedCapacity() {
        if (capacity == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return capacity - items.size();
    }

    public ArrayList<Item> getItemList () {
        ArrayList<Item> itemList = new ArrayList<>();
        for (String s : items.keySet()) {
            if (items.get(s) == null) continue;
            itemList.add(items.get(s).peekLast());
        }
        return itemList;
    }


    public static class ItemStack {

        private List<Item> items = new ArrayList<>();

        public List<Item> getItems() {
            return items;
        }

        public void add(Item item) {
            items.add(item);
        }

        public Item removeLast() {
            if (items.isEmpty()) return null;
            return items.remove(items.size() - 1);
        }

        public List<Item> removeN(int n) {
            List<Item> removed = new ArrayList<>();
            for (int i = 0; i < n && !items.isEmpty(); i++) {
                removed.add(removeLast());
            }
            return removed;
        }

        public List<Item> getAll() {
            return new ArrayList<>(items);
        }

        public Item peekLast() {
            if (items.isEmpty()) return null;
            return items.get(items.size() - 1);
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public int getCount() {
            return items.size();
        }
    }

}
