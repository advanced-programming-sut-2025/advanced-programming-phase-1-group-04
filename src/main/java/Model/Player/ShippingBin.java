package Model.Player;

import Model.Map.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShippingBin {
    private final HashMap<Item, Integer> items = new HashMap<>();

    public void add(Item item, int count) {
        items.put(item, count);
    }
}
