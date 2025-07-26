package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Controller.LoginMenuController;
import io.Ap.StardewValley.Model.Result;
import io.Ap.StardewValley.StardewValley;


public class PreGameMenuScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final TextButton backButton, startButton;
    private final ImageButton leftImageButton, rightImageButton;
    private final Image backgroundImage, charcterBackground;
    private final TextureRegion body, hand;
    private final TextureRegion[][] shirtSheet, hairSheet, pantSheet;

    public PreGameMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = StardewValley.getSkin();

        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_start.png")));
        charcterBackground = new Image(new Texture(Gdx.files.internal("etc/menu/daybg.png")));

        Texture bodyTexture = new Texture("player/body_boy.png");
        body = new TextureRegion(bodyTexture, 0, 0, 16, 32);
        Texture handTexture = new Texture("player/hand_01.png");
        hand = new TextureRegion(handTexture, 0, 0, 16, 32);

        shirtSheet = TextureRegion.split(new Texture("player/clothes/shirts.png"), 8, 8);
        hairSheet = TextureRegion.split(new Texture("player/clothes/hairstyles.png"), 16, 32);
        pantSheet = TextureRegion.split(new Texture("player/clothes/pants.png"), 16, 32);

        backButton = new TextButton("Back", skin);
        startButton = new TextButton("Start", skin);

        leftImageButton = new ImageButton(skin, "Left");
        rightImageButton = new ImageButton(skin, "Right");
    }

    @Override
    public void show() {
        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);
        stack.add(backgroundImage);

        Window window = new Window("", skin);
        Label titleLabel = new Label("PreGame", skin, "Bold");
        titleLabel.setAlignment(Align.center);
        window.getTitleTable().clear();
        window.getTitleTable().add(titleLabel).expandX().center().padTop(5).padBottom(10);

        window.setMovable(false);
        window.setResizable(false);
        window.setSize(1200, 800);
        window.setPosition(
                (stage.getWidth() - window.getWidth()) / 2,
                (stage.getHeight() - window.getHeight()) / 2
        );

        Table contentTable = new Table();

        // left column
        Table leftColumn = new Table();
        leftColumn.add(new Label("Hair", skin)).left().pad(5);
        leftColumn.row();

        leftColumn.row();
        leftColumn.add(new Label("Shirt", skin)).left().pad(5);
        leftColumn.row();

        leftColumn.row();




        // right column:

        Table rightColumn = new Table();

        rightColumn.add(new Label("", skin)).left().pad(5);
        rightColumn.row();

        rightColumn.add(new Label("Stay logged in:", skin)).left().pad(5);
        rightColumn.row();

        rightColumn.add(new Label("", skin)).left().pad(5);
        rightColumn.row();

        rightColumn.row();




        contentTable.add(leftColumn).top().pad(10);
        contentTable.add(rightColumn).top().pad(10);

        // back and next buttons:
        Table buttonRow = new Table();

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new StartMenuScreen());
            }
        });

        buttonRow.add(startButton).width(200).height(90).pad(10);
        buttonRow.add(backButton).width(200).height(90).pad(10);


        window.add(contentTable).expand().center().row();
        window.add(buttonRow).padTop(20);

        stage.addActor(window);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}