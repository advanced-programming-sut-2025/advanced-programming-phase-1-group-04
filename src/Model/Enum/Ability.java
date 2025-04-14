package Model.Enum;

public enum Ability {
    Farming (5),
    Mining (10),
    Foraging (10),
    Fishing (5);

    private final int value;

    Ability(int value) {
        this.value = value;
    }
}
