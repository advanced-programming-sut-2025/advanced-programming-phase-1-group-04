package Model.Shop;

import Model.Result;

public interface Shop {
    public ShopType getType();
    public String getName();
    public String getOwnerName();
    public int getOpeningTime();
    public int getClosingTime();

    public String getShopInformation();
    public String showAllProducts();
    public String showAvailableProducts();

    public Result buy (String productName, int number, String animalName);

    public void resetStock();
}