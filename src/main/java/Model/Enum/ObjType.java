package Model.Enum;

public enum ObjType {
    Coin(1),
    Wood(3);

    private final int value;

    ObjType(int value) {
        this.value = value;
    }
}
