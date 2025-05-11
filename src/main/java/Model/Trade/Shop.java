package Model.Trade;

import java.util.ArrayList;

public class Shop {
    private ShopType shopType;
    private ArrayList <Product> products = new ArrayList<>();

    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ShopType getShopType() {
        return shopType;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
}
