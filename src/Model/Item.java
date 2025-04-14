package Model;

public class Item {
    protected TypeObj type;

    public Item(String name) {
        this.type = TypeObj.valueOf(name);
    }
}
