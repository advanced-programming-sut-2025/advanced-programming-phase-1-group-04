package Model;

import Model.Enum.ObjType;

public class Item {
    protected ObjType type;

    public Item(String name) {
        this.type = ObjType.valueOf(name);
    }
}
