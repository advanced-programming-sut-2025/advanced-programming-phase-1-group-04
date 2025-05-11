package Model.Shop;

public enum BlackSmithProduct {
    CopperOre("A common ore that can be smelted into bars." , "Copper Ore" , 75 , -1),
    IronOre("A fairly common ore that can be smelted into bars." , "Iron Ore" , 150 , -1),
    Coal("A combustible rock that is useful for crafting and smelting." , "Coal" , 150 , -1),
    GoldOre("A precious ore that can be smelted into bars." , "Gold Ore" , 400 , -1),

    CopperTool("CopperBar" , "Copper Tool" , 2000 , 1),
    SteelTool("IronBar" , "Steel Tool" , 5000 , 1),
    GoldTool("GoldBar" , "Gold Tool" , 10000 , 1),
    IridiumTool("IridiumBar" , "Iridium Tool" , 25000 , 1),

    ChopperTrashCan("ChopperBar" , "Chopper Trash Can" , 1000 , 1),
    SteelTrashCan("SteelBar" , "Steel Trash Can" , 2500 , 1),
    GoldTrashCan("GoldBar" , "Gold Trash Can" , 5000 , 1),
    IridiumTrashCan("IridiumBar" , "Iridium Trash Can"  , 12500 , 1);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;

    BlackSmithProduct(String description, String name, int price, int dailyLimit) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }
}
