package io.Ap.StardewValley.Screen.InventoryScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Item;
import io.Ap.StardewValley.StardewValley;

import java.util.ArrayList;
import java.util.List;


public class InventoryBar extends Stage {

    private final Skin skin;
    private final ScrollPane scrollPane;
    private final Table inventoryTable;
    private final List<ImageTextButton> slotButtons;
    private int selectedIndex = -1;
    private boolean noProblem = false;

    public InventoryBar() {
        super(new ScreenViewport());

        skin = StardewValley.getSkin();
        slotButtons = new ArrayList<>();

        Table root = new Table();
        root.setFillParent(true);
        root.top().left();

        inventoryTable = new Table();

        double boxNumbers;
        try {
            boxNumbers = App.getGame().getCurrentPlayer().getInventoryCapacity();
            noProblem = true;
        } catch (Exception e) {
            boxNumbers = 12;
        }

        if (boxNumbers > 30) boxNumbers = 200;


        for (int i = 0; i < boxNumbers; i++) {

            ImageTextButton.ImageTextButtonStyle newStyle = new ImageTextButton.ImageTextButtonStyle(
                    skin.get(ImageTextButton.ImageTextButtonStyle.class) // کپی از استایل اصلی
            );
            ImageTextButton slot = new ImageTextButton("", newStyle);

            float size = 90f;

            final int index = i;
            slot.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setSelectedIndex(index);
                }
            });

            inventoryTable.add(slot).size(size).pad(0).row();
            slotButtons.add(slot);
        }

        if (noProblem) {
            int i = 0;
            for (Item item : App.getGame().getCurrentPlayer().getInventory().getItemList()) {
                String itemName = item.getName();
                setSlotImage(i, ItemTextureBank.getTexture(itemName));
                int quantity = App.getGame().getCurrentPlayer().getInventory().getItemQuantity(item);
                if (quantity > 1) {
                    setSlotText(i, Integer.toString(quantity));
                }
                i++;
            }
        }

        scrollPane = new ScrollPane(inventoryTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        root.add(scrollPane).width(140).expandY().left();
        this.addActor(root);
    }

    // ===================== Public Methods =====================

    public ScrollPane getInventoryScrollPane() {
        return scrollPane;
    }

    public void setSlotText(int index, String text) {
        if (index >= 0 && index < slotButtons.size()) {
            slotButtons.get(index).setText(text != null ? text : "");
        }
    }

    public void setSlotImage(int index, Texture texture) {
        if (index >= 0 && index < slotButtons.size()) {
            Drawable image = texture != null
                    ? new TextureRegionDrawable(new TextureRegion(texture))
                    : null;
            slotButtons.get(index).getStyle().imageUp = image;
            // لازم نیست imageDown ست کنیم مگه بخوایم متفاوت باشه
        }
    }

    public void clearSlot(int index) {
        setSlotText(index, "");
        setSlotImage(index, null);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int index) {
        if (index < 0 || index >= slotButtons.size()) return;

        // اگه این اسلات عکس نداره، سلکت نکن
        Drawable image = slotButtons.get(index).getStyle().imageUp;
        if (image == null) {
            slotButtons.get(index).setChecked(false);
            return;
        }

        // همه رو reset کن
        for (int i = 0; i < slotButtons.size(); i++) {
            slotButtons.get(i).setChecked(false);
        }

        // فقط این یکی رو active کن
        slotButtons.get(index).setChecked(true);
        selectedIndex = index;
    }


    public ImageTextButton getSlotButton(int index) {
        if (index >= 0 && index < slotButtons.size()) {
            return slotButtons.get(index);
        }
        return null;
    }

    public int getSlotCount() {
        return slotButtons.size();
    }


    public void updateInventoryBar() {
        if (!noProblem) return;

        List<Item> items = App.getGame().getCurrentPlayer().getInventory().getItemList();

        for (int i = 0; i < slotButtons.size(); i++) {
            if (i < items.size()) {
                Item item = items.get(i);
                String itemName = item.getName();
                Texture texture = ItemTextureBank.getTexture(itemName);
                setSlotImage(i, texture);

                int quantity = App.getGame().getCurrentPlayer().getInventory().getItemQuantity(item);
                if (quantity > 1) {
                    setSlotText(i, Integer.toString(quantity));
                } else {
                    setSlotText(i, "");  // اگه فقط یکیه، متن خالی بذار
                }
            } else {
                clearSlot(i); // خونه‌های اضافی رو خالی کن
            }
        }
    }

}



/*
        // ساخت جدول اصلی که سمت چپ اینونتوری و وسط محتوای پنجره رو بچینه
        Table mainLayout = new Table();
        mainLayout.setFillParent(true);


        InventoryBar inventoryBar = new InventoryBar(); // فرض می‌کنیم کلاس Inventory رو طبق راهنمای قبلی ساختی

        ScrollPane inventoryScrollPane = inventoryBar.getInventoryScrollPane(); // تابع getInventoryScrollPane رو اضافه می‌کنی به کلاس Inventory

        mainLayout.add(inventoryScrollPane).width(130).height(800).pad(50, 100, 50, 0); // سمت چپ نوار
        mainLayout.add().expand(); // جای خالی برای window وسط

        inventoryBar.setSlotImage(0,new Texture("inventory/axe.png"));

        stack.add(mainLayout);
 */





/* TODO: in game screen:
  public void show() {
        //stage = new Stage(new ScreenViewport(), App.getSharedBatch());
        stage = new Stage(new ScreenViewport());
        table.setFillParent(true);
        table.top().left();
        stage.addActor(table);
        Gdx.input.setInputProcessor(this);


/*        //TODO: inventory bar

        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);

        // ساخت جدول اصلی که سمت چپ اینونتوری و وسط محتوای پنجره رو بچینه
        Table mainLayout = new Table();
        mainLayout.setFillParent(true);

        InventoryBar inventoryBar = new InventoryBar(); // فرض می‌کنیم کلاس Inventory رو طبق راهنمای قبلی ساختی

        ScrollPane inventoryScrollPane = inventoryBar.getInventoryScrollPane(); // تابع getInventoryScrollPane رو اضافه می‌کنی به کلاس Inventory

        mainLayout.add(inventoryScrollPane).width(130).height(800).pad(50, 100, 50, 0); // سمت چپ نوار
        mainLayout.add().expand(); // جای خالی برای window وسط

        inventoryBar.setSlotImage(0,new Texture("inventory/axe.png"));

        stack.add(mainLayout);
*/
