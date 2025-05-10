package Model.Map;

public enum Symbols {
    Player("P"),

    Mountain("M"),
    Lake("L"),
    Mine("X"),
    Door("D"),

    Tree("T"),
    Foraging("^"),

    House("H"),
    GreenHouse("9"),

    FishShop("F"),
    JojaMart("J"),
    Blacksmith("B"),
    PierresGeneralStore("I"),
    MarniesRanch("R"),
    CarpentersShop("C"),
    TheStarDropSaloon("K")
    ;

    private String symbol;

    Symbols(String symbol) {
        this.symbol = symbol;
    }
}
