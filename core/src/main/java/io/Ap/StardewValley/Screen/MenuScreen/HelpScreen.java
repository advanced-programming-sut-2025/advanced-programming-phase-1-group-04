package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;

import java.util.Random;

public class HelpScreen implements Screen {
    private final Stage stage;
    private final Table mainTable;

    private final TextButton registerButton, loginButton, exitButton;
    private final Image backgroundImage;

    private final Animation<TextureRegion> birdAnimation;

    public HelpScreen() {
        Skin skin = StardewValley.getSkin();

        registerButton = new TextButton("Register", skin, "Chicken");
        loginButton = new TextButton("Login", skin, "Strawberry");
        exitButton = new TextButton("Exit", skin, "Plant");

        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_chill.png")));

        Texture birdSheet = new Texture(Gdx.files.internal("player/farmer_base.png"));
        TextureRegion[][] tmp = TextureRegion.split(birdSheet, 16, 32);
        TextureRegion[] birdFrames = new TextureRegion[18];
        System.arraycopy(tmp[0], 0, birdFrames, 0, 18);

        birdAnimation = new Animation<>(0.13f, birdFrames);

        mainTable = new Table();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);

        stack.add(backgroundImage);

        Group cloudLayer = new Group();
        stack.add(cloudLayer);

        stack.add(mainTable);

        mainTable.setFillParent(true);
        mainTable.center().top().padTop(100);

        animationActor bird1 = new animationActor(birdAnimation, 1920, 375, 5f, animationActor.MovementType.Liner);
        stage.addActor(bird1);


        Table buttonRow = new Table();
        buttonRow.add(registerButton).width(240).pad(10);
        buttonRow.add(loginButton).width(240).pad(10);
        buttonRow.add(exitButton).width(240).pad(10);
        mainTable.add(buttonRow).center().row();

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new Shash());
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new MainMenuScreen());
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
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