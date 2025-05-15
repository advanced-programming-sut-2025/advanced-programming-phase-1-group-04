package Model.Player;

import Model.Map.Item;

public class Gift implements Item {
    private final GiftType type;

    public Gift (GiftType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        return type.getSellPrice();
    }

    public GiftType getType() {
        return type;
    }
}
