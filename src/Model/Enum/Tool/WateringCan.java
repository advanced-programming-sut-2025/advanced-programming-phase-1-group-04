package Model.Enum.Tool;

public enum WateringCan {
    Starter (40, 5),
    Copper (55, 4),
    Steel (70, 3),
    Gold (85, 2),
    Iridium (100, 1);

    private final int capacity;
    private final int energy;

    WateringCan(int capacity, int energy) {
        this.capacity = capacity;
        this.energy = energy;
    }
}
