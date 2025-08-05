//package io.Ap.StardewValley.Screen.InventoryScreen;
//
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Window;
//import com.badlogic.gdx.utils.Align;
//
//public class SettingTab extends Window {
//    private final Skin skin;
//    private final TextButton exitButton;
//    public SettingTab(Skin skin) {
//        super("", skin);
//        this.skin = skin;
//
//        exitButton = new TextButton("Exit", skin);
//
//        this.setSize(1050, 650);
//        this.setMovable(false);
//        this.setResizable(false);
//        this.align(Align.topLeft);
//        this.defaults().pad(10);
//
//        this.add(exitButton).width(300).center();
//    }
//}



package io.Ap.StardewValley.Screen.InventoryScreen;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class SettingTab extends Window {

    public SettingTab(Skin skin) {
        super("", skin);

        this.setSize(1050, 650);
        this.setMovable(false);
        this.setResizable(false);
        this.align(Align.topLeft);
        this.defaults().pad(10);

        // جدول اصلی دو ستونی
        Table mainTable = new Table();
        mainTable.defaults().pad(10).top();

        // ستون چپ
        Table leftTable = new Table();
        Label settingsLabel = new Label("Settings", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit button clicked");
            }
        });

        leftTable.add(settingsLabel).center().row();
        leftTable.add(exitButton).width(200).center().row();

        // ستون راست
        Table rightTable = new Table();
        Label cheatLabel = new Label("Cheat", skin);
        rightTable.add(cheatLabel).center().row();

        TextButton cheatButton1 = new TextButton("time", skin);
        cheatButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        TextButton cheatButton2 = new TextButton("storm", skin);
        cheatButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        TextButton cheatButton3 = new TextButton("energy", skin);
        cheatButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

//        TextButton cheatButton4 = new TextButton("Cheat 4", skin);
//        cheatButton4.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Cheat 4 clicked");
//            }
//        });
//
//        TextButton cheatButton5 = new TextButton("Cheat 5", skin);
//        cheatButton5.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Cheat 5 clicked");
//            }
//        });

        rightTable.add(cheatButton1).width(200).center().row();
        rightTable.add(cheatButton2).width(200).center().row();
        rightTable.add(cheatButton3).width(200).center().row();
//        rightTable.add(cheatButton4).width(200).center().row();
//        rightTable.add(cheatButton5).width(200).center().row();

        // اضافه کردن دو ستون به جدول اصلی
        mainTable.add(leftTable).expand().top();
        mainTable.add(rightTable).expand().top();

        // اضافه کردن جدول اصلی به پنجره
        this.add(mainTable).expand().fill();
    }
}
