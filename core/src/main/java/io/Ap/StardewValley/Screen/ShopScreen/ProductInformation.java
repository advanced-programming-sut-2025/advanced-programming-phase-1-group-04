package io.Ap.StardewValley.Screen.ShopScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import io.Ap.StardewValley.Model.Result;
import io.Ap.StardewValley.Model.Shop.ProductData;
import io.Ap.StardewValley.Model.Shop.Shop;

public class ProductInformation extends Window {

    private Skin skin;
    private Stage stage;
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

    public ProductInformation(Skin skin, ProductData product, Shop shop, Stage stage) {
        super("", skin);
        this.shop = shop;
        this.product = product;
        this.skin = skin;
        this.stage = stage;

        setSize(350, 700);
        setMovable(false);
        setResizable(false);
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

        // Buy button
        buyButton = new TextButton("Buy", skin);

        // -------------------------------
        // ساخت layout اصلی
        Table rootTable = new Table(skin);
        rootTable.setFillParent(false);

        // بخش بالایی: اطلاعات محصول
        Table infoTable = new Table(skin);
        infoTable.top().left().pad(20);
        infoTable.add(nameLabel).left().expandX().fillX().row();
        infoTable.add(descriptionLabel).left().expandX().fillX().padTop(10).row();

        // بخش پایینی: quantity و buy button
        Table bottomTable = new Table(skin);
        bottomTable.center().pad(20);

        Table quantityTable = new Table(skin);
        quantityTable.add(minusButton).size(50, 50).padRight(10);
        quantityTable.add(quantityLabel).width(50).center();
        quantityTable.add(plusButton).size(50, 50).padLeft(10);

        bottomTable.add(quantityTable).padBottom(20).row();
        bottomTable.add(buyButton).padTop(10);

        // ترکیب در rootTable
//        rootTable.add(infoTable).expand().fill().row(); // بالا، expandable
//        rootTable.add(bottomTable).fillX(); // پایین، ثابت

        rootTable.add(infoTable).fillX().row(); // بدون expand
        rootTable.add(bottomTable).expandY().bottom().fillX(); // بچسبه به کف

        add(rootTable).expand().fill();

        addBuyListener();
    }

    private void updateQuantityLabel() {
        quantityLabel.setText(String.valueOf(quantity));
    }

    public void addBuyListener() {
        ClickListener listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = shop.buy(product.getName(), quantity, null);
                if (result.isSuccessful()) {
                    shopStockNeedsUpdate = true;
                } else {
                    showError(result.message());
                }
            }
        };
        buyButton.addListener(listener);
    }

    public void update(ProductData product) {
        this.product = product;
        nameLabel.setText(product.getName());
        descriptionLabel.setText(product.getDescription());
        quantity = 1;
        updateQuantityLabel();
    }

    public boolean shopStockNeedsUpdate() {
        return shopStockNeedsUpdate;
    }

    public void setShopStockNeedsUpdate(boolean shopStockNeedsUpdate) {
        this.shopStockNeedsUpdate = shopStockNeedsUpdate;
    }

    private void showError(String msg) {
        final Window errorWindow = new Window("", skin, "Letter");
        errorWindow.setMovable(false);
        errorWindow.setKeepWithinStage(true);
        errorWindow.add(new Label(msg, skin));
        errorWindow.setSize(700, 90);
        errorWindow.setPosition(600, 170, Align.center);
        errorWindow.pack();

        stage.addActor(errorWindow);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                errorWindow.remove();
            }
        }, 5);
    }
}

