package Model.Shop.BlackSmith;

public enum UpgradeTools {
    CopperTool("CopperBar" , "Copper Tool" , 2000 , 1),
    SteelTool("IronBar" , "Steel Tool" , 5000 , 1),
    GoldTool("GoldBar" , "Gold Tool" , 10000 , 1),
    IridiumTool("IridiumBar" , "Iridium Tool" , 25000 , 1),
    ChopperTrashCan("ChopperBar" , "Chopper Trash Can" , 1000 , 1),
    SteelTrashCan("SteelBar" , "Steel Trash Can" , 2500 , 1),
    GoldTrashCan("GoldBar" , "Gold Trash Can" , 5000 , 1),
    IridiumTrashCan("IridiumBar" , "Iridium Trash Can"  , 12500 , 1);

    private final String ingredient;
    private final String name;
    private final int cost;
    private final int dailyLimit;

    UpgradeTools(String ingredient, String name, int cost, int dailyLimit) {
        this.ingredient = ingredient;
        this.name = name;
        this.cost = cost;
        this.dailyLimit = dailyLimit;
    }


}
