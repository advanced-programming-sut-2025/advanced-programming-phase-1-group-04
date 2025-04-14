package Model.Enum;

public enum TypeObj {
    Coin(1),
    Wood(3);

    private final int value;

    TypeObj(int value) {
        this.value = value;
    }
}
