package Model.Tool;

public enum Hoe {
    Starter (5),
    Copper (4),
    Steel (3),
    Gold (2),
    Iridium (1);

    private final int energy;

    Hoe(int energy) {
        this.energy = energy;
    }
}
