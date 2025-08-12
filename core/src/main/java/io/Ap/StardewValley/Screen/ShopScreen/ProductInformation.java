package io.Ap.StardewValley.Screen.ShopScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.Ap.StardewValley.Model.Shop.ProductData;
import io.Ap.StardewValley.Model.Shop.Shop;

public class ProductInformation extends Window {

    private Shop shop;
    private ProductData product;
    private final Label nameLabel;
    private final Label descriptionLabel;

    private final Label quantityLabel;
    private final ImageButton plusButton;
    private final ImageButton minusButton;

    private final TextButton buyButton;

    private int quantity = 1;

    private boolean shopStockNeedsUpdate = false;

    public ProductInformation(Skin skin, ProductData product, Shop shop) {
        super("", skin);
        this.shop = shop;
        this.product = product;

        setSize(450, 700);
        setMovable(false);
        setResizable(false);
//        setModal(true);
        setModal(false);
        setKeepWithinStage(true);

        // Labels for name and description
        nameLabel = new Label(product.getName(), skin);
        descriptionLabel = new Label(product.getDescription(), skin);

        nameLabel.setAlignment(Align.left);
        descriptionLabel.setAlignment(Align.left);
        descriptionLabel.setWrap(true);

        // Quantity controls
        quantityLabel = new Label(String.valueOf(quantity), skin);
        quantityLabel.setAlignment(Align.center);

        plusButton = new ImageButton(skin, "Right");
        minusButton = new ImageButton(skin, "Left");

        // Add listeners to plus and minus buttons
        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                quantity++;
                updateQuantityLabel();
            }
        });

        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (quantity > 1) {
                    quantity--;
                    updateQuantityLabel();
                }
            }
        });

        // Buy button (listener to be added by user)
        buyButton = new TextButton("Buy", skin);

        // Layout setup
        Table mainTable = new Table(skin);
        mainTable.top().center().pad(20);

        // Add name and description
        mainTable.add(nameLabel).left().expandX().fillX().row();
        mainTable.add(descriptionLabel).left().expandX().fillX().padTop(10).row();

        // Quantity controls: minus, quantity, plus
        Table quantityTable = new Table(skin);
        quantityTable.add(minusButton).size(50, 50).padRight(10);
        quantityTable.add(quantityLabel).width(50).center();
        quantityTable.add(plusButton).size(50, 50).padLeft(10);

        mainTable.add(quantityTable).center().padTop(300).row();

        // Buy button at bottom
        mainTable.add(buyButton)/*.expandX().fillX()*/.padTop(50).row();

        add(mainTable).expand().fill();

        addBuyListener();
    }

    private void updateQuantityLabel() {
        quantityLabel.setText(String.valueOf(quantity));
    }

    // متد برای اضافه کردن listener به دکمه Buy
    public void addBuyListener() {
        ClickListener listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (shop.buy(product.getName(), quantity, null).isSuccessful()) {
                    shopStockNeedsUpdate = true;
                }
            }
        };
        buyButton.addListener(listener);
    }

    // متد update برای بروز کردن اطلاعات محصول
    public void update(ProductData product) {
        this.product = product;
        nameLabel.setText(product.getName());
        descriptionLabel.setText(product.getDescription());
        quantity = 1;
        updateQuantityLabel();
    }

    // متد برای گرفتن تعداد انتخاب شده
    public int getQuantity() {
        return quantity;
    }

    public boolean shopStockNeedsUpdate() {
        return shopStockNeedsUpdate;
    }

    public void setShopStockNeedsUpdate(boolean shopStockNeedsUpdate) {
        this.shopStockNeedsUpdate = shopStockNeedsUpdate;
    }
}