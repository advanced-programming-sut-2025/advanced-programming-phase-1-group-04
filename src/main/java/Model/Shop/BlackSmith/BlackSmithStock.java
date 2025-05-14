package Model.Shop.BlackSmith;

import Model.Plants.ForagingMineralType;
import Model.Shop.ProductInterface;

public enum BlackSmithStock implements ProductInterface {
    CopperOre(ForagingMineralType.Copper, "A common ore that can be smelted into bars." , "Copper Ore" , 75 , Integer.MAX_VALUE),
    IronOre(ForagingMineralType.Iron, "A fairly common ore that can be smelted into bars." , "Iron Ore" , 150 , Integer.MAX_VALUE),
    Coal(ForagingMineralType.Coal, "A combustible rock that is useful for crafting and smelting." , "Coal" , 150 , Integer.MAX_VALUE),
    GoldOre(ForagingMineralType.Gold, "A precious ore that can be smelted into bars." , "Gold Ore" , 400 , Integer.MAX_VALUE);

    private final String description;
    private final String name;
    private final int price;
    private final int dailyLimit;
    private final ForagingMineralType mineralType;

    BlackSmithStock(ForagingMineralType mineralType, String description, String name, int price, int dailyLimit) {
        this.mineralType = mineralType;
        this.description = description;
        this.name = name;
        this.price = price;
        this.dailyLimit = dailyLimit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getDailyLimit() {
        return dailyLimit;
    }

    public ForagingMineralType getMineralType() {
        return mineralType;
    }
}
