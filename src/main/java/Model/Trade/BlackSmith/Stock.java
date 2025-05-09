package Model.Trade.BlackSmith;

public enum Stock {
    CopperOre("A common ore that can be smelted into bars." , "Copper Ore" , 75 , -1),
    IronOre("A fairly common ore that can be smelted into bars." , "Iron Ore" , 150 , -1),
    Coal("A combustible rock that is useful for crafting and smelting." , "Coal" , 150 , -1),
    GoldOre("A precious ore that can be smelted into bars." , "Gold Ore" , 400 , -1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    Stock(String description, String name, int price, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }


}
