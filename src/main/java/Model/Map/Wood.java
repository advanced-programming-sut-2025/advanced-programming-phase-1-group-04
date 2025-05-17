package Model.Map;

import Model.Shop.CarpentersShop.CarpenterStock;

public class Wood implements Item{
    @Override
    public String getName() {
        return "Wood";
    }

    @Override
    public int getPrice() {
        return CarpenterStock.Wood.getPrice() / 2;
    }
}
