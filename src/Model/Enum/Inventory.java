package Model.Enum;

public enum Inventory {
    Starter (12),
    Large (24),
    Deluxe (-1); // for infinity

    private final int capacity;

    Inventory(int capacity) {
        this.capacity = capacity;
    }
}