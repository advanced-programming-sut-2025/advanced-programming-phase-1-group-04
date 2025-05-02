package Model.Tool;

public enum FishingPole {
    Training (8),
    Bamboo (8),
    Fiberglass (6),
    Iridium (4);

    private final int energy;

    FishingPole(int energy) {
        this.energy = energy;
    }
}
