package Model.Map;

public enum Symbols {
    Player("P"),

    Mountain("M"),
    Lake("L"),
    Mine("X"),

    Ground("G"),
    Tree("T"),
    Foraging("^"),
    Pavement("*"),

    House("H"),
    GreenHouse("9"),
    //Door("D"),

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

    public String getSymbol() {
        return symbol;
    }
}
