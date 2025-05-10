package Model;

import Model.Enum.Season;
import Model.Enum.WeekDay;
import Model.Trade.JojaMart.SpringStock;
import Model.Trade.Product;
import Model.Trade.Shop;
import Model.Trade.ShopType;

import java.util.ArrayList;

public class Game {
    public static ArrayList<User> users;
    public static User mainUser;
    private static DateAndTime currentTime = new DateAndTime (9, 1, WeekDay.Monday, Season.Spring);

    public Game(ArrayList<User> users, User mainUser) {
        this.users = users;
        this.mainUser = mainUser;
        /*Shop shop = new Shop();
        shop.setShopType(ShopType.CarpentersShop);
        for (SpringStock moz : SpringStock.values()) {
            Product product = new Product();
            product.setProductType(moz);
            product.setDailyLimit(moz.getDailyLimit());
            shop.addProduct(product);
        }*/
    }

    public static DateAndTime getCurrentTime() {
        return currentTime;
    }

    public static DateAndTime getCurrentTime() {
        return currentTime;
    }
}