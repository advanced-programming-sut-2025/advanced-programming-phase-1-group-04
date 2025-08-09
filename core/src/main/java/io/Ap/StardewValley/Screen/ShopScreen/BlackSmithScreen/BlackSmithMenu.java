package io.Ap.StardewValley.Screen.ShopScreen.BlackSmithScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Model.Shop.BlackSmith.BlackSmith;
import io.Ap.StardewValley.Screen.ShopScreen.FirstMenu;
import io.Ap.StardewValley.StardewValley;

public class BlackSmithMenu extends Stage {

    private final Skin skin = StardewValley.getSkin();
    private BlackSmithShopMenu blackSmithShopMenu;
    private BlackSmithToolsMenu blackSmithToolsMenu;
    private FirstMenu blackSmithFirstMenu;
    private TextButton shopMenuButton, toolsButton, leaveButton;
    private BlackSmith blackSmith = new BlackSmith();

    boolean isShopMenuVisible = false;
    boolean isToolsMenuVisible = false;

    float windowWidth = 1050;
    float windowHeight = 650;
    float windowX = (getViewport().getScreenWidth() - windowWidth) / 2f;
    float windowY = (getViewport().getScreenHeight() - windowHeight) / 2f;

    float buttonWidth = 1000;
    float buttonHeight = 100;

    public BlackSmithMenu() {
        super(new ScreenViewport());

        makeShopButton();
        makeToolsButton();
        makeLeaveButton();

        blackSmithFirstMenu = new FirstMenu(skin, "Blacksmith", shopMenuButton, toolsButton, leaveButton);
        blackSmithFirstMenu.setPosition((getViewport().getScreenWidth() - 1050) / 2f, 100);
        blackSmithFirstMenu.setVisible(true);
        this.addActor(blackSmithFirstMenu);

        blackSmithShopMenu = new BlackSmithShopMenu(skin);
        blackSmithShopMenu.setPosition(500, 540);
        blackSmithShopMenu.setVisible(false);
        this.addActor(blackSmithShopMenu);



    }

//    @Override
//    public Actor hit(float stageX, float stageY, boolean touchable) {
//        Actor hit = super.hit(stageX, stageY, touchable);
//
//        for (Window win : infoWindows) {
//            if (hit == win) return null;
//        }
//        return hit;
//    }

    public void setVisibleAll(boolean visible) {

        if (!isShopMenuVisible && !isToolsMenuVisible) {
            blackSmithFirstMenu.setVisible(visible);
        }

        if (!visible) {
            blackSmithShopMenu.setVisible(false);
//            blackSmithToolsMenu.setVisible(false);
            isShopMenuVisible = false;
            isToolsMenuVisible = false;
        }

        if (visible) {
            this.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            this.draw();
        }
    }

    public void update() {
//        try {
//            ((RefrigeratorTab) refrigeratorTab).update();
//        } catch (Exception e) {
//        }
//        try {
//            ((CookingTab) cookingTab).update();
//        } catch (Exception e) {
//        }
    }

    private void makeShopButton() {
        shopMenuButton = new TextButton("shop", skin);
        shopMenuButton.setSize(buttonWidth, buttonHeight);

        shopMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                blackSmithFirstMenu.setVisible(false);
                blackSmithShopMenu.setVisible(true);
                blackSmithShopMenu.toFront();
                isShopMenuVisible = true;
            }
        });
        this.addActor(shopMenuButton);
    }

    private void makeToolsButton() {
        toolsButton = new TextButton("upgrade tools", skin);
        toolsButton.setSize(buttonWidth, buttonHeight);

        toolsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                for (int j = 0; j < buttons.size(); j++) {
//                    buttons.get(j).setChecked(false);
//                    infoWindows.get(j).setVisible(false);
//                }
//
//                cookingButton.setChecked(true);
//                infoWindows.get(1).setVisible(true);
//                infoWindows.get(1).toFront();
//                activeButton = cookingButton;
            }
        });
        this.addActor(toolsButton);
    }

    private void makeLeaveButton() {
        leaveButton = new TextButton("leave", skin);
        leaveButton.setSize(buttonWidth, buttonHeight);

        leaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                for (int j = 0; j < buttons.size(); j++) {
//                    buttons.get(j).setChecked(false);
//                    infoWindows.get(j).setVisible(false);
//                }
//
//                refrigeratorButton.setChecked(true);
//                infoWindows.get(0).setVisible(true);
//                infoWindows.get(0).toFront();
//                activeButton = refrigeratorButton;
            }
        });
        this.addActor(leaveButton);
    }
}
