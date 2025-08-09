package io.Ap.StardewValley.Screen.ShopScreen.BlackSmithScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.Ap.StardewValley.Model.Shop.ProductData;
import io.Ap.StardewValley.Screen.ShopScreen.*;

import java.util.ArrayList;
import java.util.List;

public class BlackSmithShopMenu extends Table {
    private SellerPicture sellerPicture;
    private SellerDescription sellerDescription;
    private ShopStock shopStock;
//    private ProductInformation productInformation;

    public BlackSmithShopMenu(Skin skin) {
        super(skin);

        Table left = new Table();
        Table middle = new Table();
        Table right = new Table();

        Texture sellerTexture = new Texture("shop/Clint.png");
        sellerPicture = new SellerPicture(skin, sellerTexture, "Blacksmith");
        sellerDescription = new SellerDescription(skin, "too lazy to mine your own ore?\nNo problem!");

        left.add(sellerPicture).size(350, 350).row();
        left.add(sellerDescription).size(350, 350).row();

        List<ProductData> list = new ArrayList<>();
        list.add(new ProductData("xxx", 100, 20, "9lkjksdl"));
//
//        shopStock = new ShopStock(skin, list);
//        middle.add(middle);



        this.add(left);
        this.add(middle);
        this.add(right);
    }
}
