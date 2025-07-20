package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

public class StartMenuScreen implements Screen {
    private final Stage stage;
    private final Table mainTable;

    private final TextButton registerButton, loginButton, exitButton;
    private Image backgroundImage, logoImage;

    private final Array<Texture> cloudTextures = new Array<>();
    private final Array<CloudActor> cloudActors = new Array<>();

    public StartMenuScreen() {
        Skin skin = StardewValley.getSkin();

        registerButton = new TextButton("Register", skin, "Chicken");
        loginButton = new TextButton("Login", skin, "Strawberry");
        exitButton = new TextButton("Exit", skin, "Plant");


        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_start.png")));
        logoImage = new Image(new Texture(Gdx.files.internal("etc/menu/logo.png")));
        cloudTextures.add(new Texture(Gdx.files.internal("etc/menu/cloud_1.png")));
        cloudTextures.add(new Texture(Gdx.files.internal("etc/menu/cloud_2.png")));
        cloudTextures.add(new Texture(Gdx.files.internal("etc/menu/cloud_3.png")));

        mainTable = new Table();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Stack stack = new Stack();
        stack.setFillParent(true);
        stack.add(backgroundImage);  // background at bottom
        stack.add(mainTable);        // UI on top

        stage.addActor(stack);

        mainTable.setFillParent(true);
        mainTable.center().top().padTop(100);

        // Add logo at the top center
        mainTable.add(logoImage).center().padBottom(50).row();

        // Horizontal row of buttons
        Table buttonRow = new Table();
        buttonRow.add(registerButton).width(222).pad(10);
        buttonRow.add(loginButton).width(222).pad(10);
        buttonRow.add(exitButton).width(222).pad(10);

        mainTable.add(buttonRow).center().row();

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 StardewValley.getGame().setScreen(new SignUpMenuScreen());
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // StardewValley.getGame().setScreen(new LoginMenu());
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

