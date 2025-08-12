package io.Ap.StardewValley.Screen.ShopScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.Ap.StardewValley.Model.Shop.ProductData;
import io.Ap.StardewValley.Model.Shop.Shop;

public class ShopMenu extends Table {
    private SellerPicture sellerPicture;
    private SellerDescription sellerDescription;
    private ShopStock shopStock;
    private ProductInformation productInformation;
    private Shop shop;


    public ShopMenu(Skin skin, Shop shop, String texturePath) {
        super(skin);
        this.shop = shop;

        Table left = new Table();
        Table middle = new Table();
        Table right = new Table();

        Texture sellerTexture = new Texture(texturePath);
        sellerPicture = new SellerPicture(skin, sellerTexture, shop.getName());
        sellerDescription = new SellerDescription(skin, shop.getShopInformation());

        left.add(sellerPicture).size(350, 350).row();
        left.add(sellerDescription).size(350, 350).row();


        shopStock = new ShopStock(skin, shop.getProductData(), shop.getType());
        middle.add(shopStock).size(500, 700);

        productInformation = new ProductInformation(skin, new ProductData("", 0, 0, ""), shop);
        right.add(productInformation).size(450, 700);

        this.add(left);
        this.add(middle);
        this.add(right);

    }

    public void updateShop() {
        if (productInformation.shopStockNeedsUpdate()) {
            shopStock.update(shop.getProductData()); //todo: shop list
            productInformation.setShopStockNeedsUpdate(false);
        }
        if (shopStock.productDataNeedsUpdate()) {
            productInformation.update(shopStock.getSelectedProductData());
            shopStock.setProductDataNeedsUpdate(false);
        }
    }
}