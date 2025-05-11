package Model.Map;

public enum Symbols {
    Player("@"),

    Mountain("M"),
    Water("L"),
    Mine("X"),

    Pavement("*"),
    Ground("G"),
    Tree("T"),
    Sapling("1"),
    Crop("2"),
    Fruit("3"),
    Seed("4"),
    ForagingCrop("^"),
    ForagingMineral("<"),
    Stone("%"),
    Wood("W"),
    Craft("/"),
    Food("U"),
    Fish("Y"),
    AnimalProduct("{"),

    NPC("#"),
    Animal("$"),

    House("H"),
    GreenHouse("9"),
    //Door("D"),

    Barn("&"),
    BigBarn("="),
    DeluxeBarn("_"),
    Coop(")"),
    BigCoop("("),
    DeluxeCoop("!"),
    Well("?"),
    ShippingBin("0"),

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
