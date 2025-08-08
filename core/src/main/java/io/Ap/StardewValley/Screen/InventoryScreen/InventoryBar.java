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
import io.Ap.StardewValley.Controller.SirkBozorg.ToolController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Item.Item;
import io.Ap.StardewValley.Model.Tool.Tool;
import io.Ap.StardewValley.StardewValley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryBar extends Stage {

    private final Skin skin = StardewValley.getSkin();
    private final ScrollPane scrollPane;
    private final Table inventoryTable;
    private final List<ImageTextButton> slotButtons = new ArrayList<>();
    private final Map<Integer, Item> indexToItem = new HashMap<>();

    private int selectedIndex = -1;
    private boolean noProblem = false;
    private int lastKnownCapacity = -1;

    public InventoryBar() {
        super(new ScreenViewport());

        Table root = new Table();
        root.setFillParent(true);
        root.top().left();

        inventoryTable = new Table();

        scrollPane = new ScrollPane(inventoryTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        root.add(scrollPane).width(140).expandY().left();
        this.addActor(root);

        initializeSlots();
        loadInitialItems();
    }

    private void initializeSlots() {
        double boxNumbers;
        try {
            boxNumbers = App.getGame().getCurrentPlayer().getInventoryCapacity();
            noProblem = true;
        } catch (Exception e) {
            boxNumbers = 12;
        }

        if (boxNumbers > 30) boxNumbers = 200;
        lastKnownCapacity = (int) boxNumbers;

        for (int i = 0; i < lastKnownCapacity; i++) {
            addSlotButton(i);
        }
    }

    private void addSlotButton(int index) {
        ImageTextButton.ImageTextButtonStyle newStyle = new ImageTextButton.ImageTextButtonStyle(
                skin.get(ImageTextButton.ImageTextButtonStyle.class)
        );
        ImageTextButton slot = new ImageTextButton("", newStyle);
        final int finalIndex = index;

        slot.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setSelectedIndex(finalIndex);
            }
        });

        inventoryTable.add(slot).size(90f).pad(0).row();
        slotButtons.add(slot);
    }

    private void loadInitialItems() {
        if (!noProblem) return;

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

    private void refreshCapacityIfNeeded() {
        int actualCapacity;
        try {
            actualCapacity = App.getGame().getCurrentPlayer().getInventory().getCapacity();
        } catch (Exception e) {
            actualCapacity = 12;
        }

        if (actualCapacity > 30) actualCapacity = 200;

        if (actualCapacity == lastKnownCapacity) return;

        lastKnownCapacity = actualCapacity;

        inventoryTable.clear();
        slotButtons.clear();
        indexToItem.clear();

        for (int i = 0; i < actualCapacity; i++) {
            addSlotButton(i);
        }
    }

    public void updateInventoryBar() {
        if (!noProblem) return;

        refreshCapacityIfNeeded();

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
                    setSlotText(i, "");
                }
                indexToItem.put(i, item);
            } else {
                clearSlot(i);
                indexToItem.put(i, null);
            }
        }
    }

    public void setSlotText(int index, String text) {
        if (index >= 0 && index < slotButtons.size()) {
            slotButtons.get(index).setText(text != null ? text : "");
        }
    }

    public void setSlotImage(int index, Texture texture) {
        if (index >= 0 && index < slotButtons.size()) {
            Drawable image = (texture != null)
                    ? new TextureRegionDrawable(new TextureRegion(texture))
                    : null;
            slotButtons.get(index).getStyle().imageUp = image;
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

        Drawable image = slotButtons.get(index).getStyle().imageUp;
        if (image == null) {
            slotButtons.get(index).setChecked(false);
            return;
        }

        for (ImageTextButton button : slotButtons) {
            button.setChecked(false);
        }

        slotButtons.get(index).setChecked(true);
        selectedIndex = index;

        try {
            Item item = getSelectedItem(index);
            if (item instanceof Tool tool) {
//                App.getGame().getCurrentPlayer().setCurrentTool(tool);
                ToolController.equipThroughScreen(tool.getName());
            }

            //TODO: تکمیل شوددددد. هر چیزی که انتخاب میشه اضافه کن.

        } catch (Exception ignored) {}
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

    public ScrollPane getInventoryScrollPane() {
        return scrollPane;
    }

    private Item getSelectedItem(int index) {
        try {
            return indexToItem.get(index);
        } catch (Exception e) {
            return null;
        }
    }
}
