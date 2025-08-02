package io.Ap.StardewValley.Screen.InventoryScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;

import java.util.ArrayList;
import java.util.List;


public class InventoryStage extends Stage {
    private final Skin skin;
    private final List<Window> infoWindows;
    private final List<ImageTextButton> buttons;
    private ImageTextButton activeButton;

    public InventoryStage() {
        super(new ScreenViewport());
        skin = StardewValley.getSkin();
        buttons = new ArrayList<>();
        infoWindows = new ArrayList<>();

        // ===== مشخصات پنجره =====
        float windowWidth = 1050;
        float windowHeight = 650;
        float windowX = (getViewport().getScreenWidth() - windowWidth) / 2f;
        float windowY = (getViewport().getScreenHeight() - windowHeight) / 2f;

        // ===== دکمه‌ها =====
        float buttonWidth = 180;
        float buttonHeight = 90;
        float spacing = 0;

        for (int i = 0; i < 5; i++) {
            // ساخت دکمه
            final int index = i;
            // ساخت استایل اختصاصی برای دکمه
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
                    skin.get("inventoryButton", ImageTextButton.ImageTextButtonStyle.class)
            );
            Texture texture = new Texture(Gdx.files.internal("inventory/inventory button " + i + ".png"));
            TextureRegionDrawable icon = new TextureRegionDrawable(new TextureRegion(texture));
            style.imageUp = icon;

            final ImageTextButton btn = new ImageTextButton("", style);

            btn.getImageCell().size(64, 64); // عرض و ارتفاع دلخواه رو بزن




            btn.setSize(buttonWidth, buttonHeight);
            float x = windowX + i * (buttonWidth + spacing) + 20;
            float y = windowY + windowHeight;
            btn.setPosition(x, y);

            // ساخت پنجره مرتبط با دکمه
            Window win = createWindow(i);
            win.setPosition(windowX, windowY);
            infoWindows.add(win);
            win.setVisible(false); // فقط بعد از کلیک فعال میشه
            this.addActor(win);
            TooltipManager.getInstance().instant();

            // لیسنر کلیک
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    // غیرفعال کردن همه‌ی دکمه‌ها و پنجره‌ها
                    for (int j = 0; j < buttons.size(); j++) {
                        buttons.get(j).setChecked(false);
                        infoWindows.get(j).setVisible(false);
                    }

                    // فعال کردن این دکمه و پنجره مربوطه
                    btn.setChecked(true);
                    infoWindows.get(index).setVisible(true);
                    infoWindows.get(index).toFront();
                    activeButton = btn;
                }
            });


            buttons.add(btn);
            this.addActor(btn);
        }

        // در ابتدا فقط دکمه اول فعال باشه و پنجره‌اش نمایش داده بشه
        buttons.get(0).setChecked(true);
        infoWindows.get(0).setVisible(true);
        infoWindows.get(0).toFront();
        activeButton = buttons.get(0);
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        Actor hit = super.hit(stageX, stageY, touchable);

        // اگر کلیک روی هیچ‌کدام از پنجره‌ها بود، رفتار رو قطع کن
        for (Window win : infoWindows) {
            if (hit == win) return null;
        }

        return hit;
    }

    public List<ImageTextButton> getButtons() {
        return buttons;
    }

    public List<Window> getInfoWindows() {
        return infoWindows;
    }

    public void setVisibleAll(boolean visible) {
        for (ImageTextButton btn : buttons)
            btn.setVisible(visible);
        for (Window win : infoWindows)
            win.setVisible(visible);
    }

    private Window createWindow(int index) {
        Window win;
        if (index == 0) {
            win = new InventoryTab((skin));
        } else if (index == 1) {
            win = new SkillsTab(skin);
        }
        else {
            win = new Window("", skin);
            win.add(new Label("Window " + (index + 1), skin)).row();
        }
        win.setMovable(false);
        win.setSize(1050, 650);
        return win;
    }
}





/*

    private InventoryStage inventoryStage;
    private boolean inventoryStageVisible = false;
    InventoryBar inventoryBar;

 */


/*

        inventoryStage = new InventoryStage();

        //TODO: inventory bar

        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);

        // ساخت جدول اصلی که سمت چپ اینونتوری و وسط محتوای پنجره رو بچینه
        Table mainLayout = new Table();
        mainLayout.setFillParent(true);

        inventoryBar = new InventoryBar(); // فرض می‌کنیم کلاس Inventory رو طبق راهنمای قبلی ساختی

        ScrollPane inventoryScrollPane = inventoryBar.getInventoryScrollPane(); // تابع getInventoryScrollPane رو اضافه می‌کنی به کلاس Inventory

        mainLayout.add(inventoryScrollPane).width(130).height(800).pad(50, 100, 50, 0); // سمت چپ نوار
        mainLayout.add().expand(); // جای خالی برای window وسط

        inventoryBar.setSlotImage(0,new Texture("inventory/axe.png"));

        stack.add(mainLayout);
//

*/



/*


        multiplexer.addProcessor(inventoryStage); // اول customStage


 */



/*



        if (inventoryStageVisible) {
            inventoryStage.setVisibleAll(true);
            inventoryStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            inventoryStage.draw();
        } else {
            inventoryStage.setVisibleAll(false);
        }
        inventoryBar.updateInventoryBar();


 */




/*
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.I) {
            inventoryStageVisible = !inventoryStageVisible;
            return true;
        }
        return false;
    }
 */