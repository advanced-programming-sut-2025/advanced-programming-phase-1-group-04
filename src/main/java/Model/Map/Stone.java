package Model.Map;

import Model.Shop.CarpentersShop.CarpenterStock;

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
