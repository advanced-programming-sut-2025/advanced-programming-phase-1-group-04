package Model.Enum;

public enum TypeTrashCan {
    Starter (0),
    Copper (15),
    Steel (30),
    Gold (45),
    Iridium (60);

    private final int percent;

    TypeTrashCan(int percent) {
        this.percent = percent;
    }
}
