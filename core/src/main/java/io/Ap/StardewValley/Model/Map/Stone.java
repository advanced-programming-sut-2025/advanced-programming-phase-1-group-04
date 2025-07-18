package io.Ap.StardewValley.Model.Map;

import io.Ap.StardewValley.Model.Shop.CarpentersShop.CarpenterStock;

public class Stone implements Item{
    @Override
    public String getName() {
        return "Stone";
    }

    @Override
    public int getPrice() {
        return CarpenterStock.Stone.getPrice() / 2;
    }
}
