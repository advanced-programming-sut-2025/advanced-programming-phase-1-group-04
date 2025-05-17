package Model.Player;

import Model.Map.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingBin {
    private final HashMap<Item, Integer> items = new HashMap<>();

    public void add(Item item, int count) {
        items.put(item, count);
    }

    public int getPrices() {
        int totalPrice = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int count = entry.getValue();
            totalPrice += item.getPrice() * count;
        }
        return totalPrice;
    }


    public void clear() {
        items.clear();
    }
}
