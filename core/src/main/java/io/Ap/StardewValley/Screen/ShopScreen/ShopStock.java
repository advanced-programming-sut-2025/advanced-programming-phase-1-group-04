package io.Ap.StardewValley.Screen.ShopScreen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.Ap.StardewValley.Model.Shop.ProductData;
import io.Ap.StardewValley.Screen.ItemScreen.ItemTextureBank;

import java.util.ArrayList;
import java.util.List;

public class ShopStock extends Window {

    private final CheckBox onlyAvailableCheckBox;
    private final ScrollPane allItemsScroll;
    private final ScrollPane availableItemsScroll;

    private final Table allItemsTable;
    private final Table availableItemsTable;

    private final List<ImageTextButton> allButtons = new ArrayList<>();
    private final List<ImageTextButton> availableButtons = new ArrayList<>();

    private final Skin skin;

    public ShopStock(Skin skin, List<ProductData> products) {
        super("Shop Stock", skin);
        this.skin = skin;

        setSize(500, 700);
        setMovable(false);
        setResizable(false);
        setModal(true);
        setKeepWithinStage(true);

        // چک‌باکس بالای ویندو
        onlyAvailableCheckBox = new CheckBox("Only availables", skin);
        onlyAvailableCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                updateVisibleScrollPane();
            }
        });
        add(onlyAvailableCheckBox).left().pad(10).colspan(1);
        row();

        // جدول ها برای دکمه ها
        allItemsTable = new Table(skin);
        availableItemsTable = new Table(skin);

        // ساخت دو اسکرول‌پین
        allItemsScroll = new ScrollPane(allItemsTable, skin);
        availableItemsScroll = new ScrollPane(availableItemsTable, skin);

        // اندازه اسکرول‌پین ها (کل فضای باقی‌مانده)
        allItemsScroll.setFadeScrollBars(false);
        availableItemsScroll.setFadeScrollBars(false);

        add(allItemsScroll).expand().fill();
        row();
        add(availableItemsScroll).expand().fill();

        // اول فقط allItemsScroll نشون داده بشه
        availableItemsScroll.setVisible(false);

        // مقداردهی اولیه با داده‌ها
        update(products);
    }


    private void updateVisibleScrollPane() {
        if (onlyAvailableCheckBox.isChecked()) {
            allItemsScroll.setVisible(false);
            availableItemsScroll.setVisible(true);
        } else {
            allItemsScroll.setVisible(true);
            availableItemsScroll.setVisible(false);
        }
    }

    public void update(List<ProductData> products) {
        // پاک کردن جدول‌ها و لیست دکمه‌ها
        allItemsTable.clear();
        availableItemsTable.clear();
        allButtons.clear();
        availableButtons.clear();

        for (ProductData product : products) {
            ImageTextButton btn = createProductButton(product);
            allButtons.add(btn);
            allItemsTable.add(btn).expandX().fillX().row();

            if (product.exists()) {
                ImageTextButton btnAvailable = createProductButton(product);
                availableButtons.add(btnAvailable);
                availableItemsTable.add(btnAvailable).expandX().fillX().row();
            }
        }

        updateVisibleScrollPane();
        pack();
    }

    private ImageTextButton createProductButton(ProductData product) {
        // گرفتن تکسچر از بانک
        Image image = new Image(ItemTextureBank.getTexture(product.getName()));

        // ساخت Table برای چیدمان داخل دکمه
        Table contentTable = new Table();

        // چپ: عکس
        contentTable.add(image).size(50, 50).padRight(10);

        // وسط: نام محصول
        Label nameLabel = new Label(product.getName(), skin);
        nameLabel.setAlignment(Align.left);
        contentTable.add(nameLabel).expandX().left();

        // راست: قیمت
        Label priceLabel = new Label(String.valueOf(product.getPrice()), skin);
        priceLabel.setAlignment(Align.right);
        contentTable.add(priceLabel).width(70).right();

        // ساخت ImageTextButton خالی و اضافه کردن contentTable بهش
        ImageTextButton button = new ImageTextButton("", skin);
        button.clearChildren();
        button.add(contentTable).expand().fill();

        // disable کردن دکمه اگر محصول موجود نیست
        if (!product.exists()) {
            button.setDisabled(true);
            nameLabel.setColor(0.6f, 0.6f, 0.6f, 1f);
            priceLabel.setColor(0.6f, 0.6f, 0.6f, 1f);
        }

        return button;
    }

    // متدی برای اضافه کردن Listener به همه دکمه‌ها
    public void addListenerToButtons(EventListener listener) {
        for (ImageTextButton btn : allButtons) {
            btn.addListener(listener);
        }
        for (ImageTextButton btn : availableButtons) {
            btn.addListener(listener);
        }
    }

}