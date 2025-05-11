package Model.Trade.FishShop;

import Model.Trade.ProductInterface;

public enum Stock implements ProductInterface {


    public int getFishingSkill() {
        return fishingSkill;
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
}
