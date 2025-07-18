package io.Ap.StardewValley.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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

    public StartMenuScreen() {
        Skin skin = StardewValley.getSkin();

        registerButton = new TextButton("Register", skin);
        loginButton = new TextButton("Login", skin);
        exitButton = new TextButton("Exit", skin);

        Texture backgroundTexture = new Texture(Gdx.files.internal("assets/etc/menu/menu_bg.jpg"));
        backgroundImage = new Image(backgroundTexture);
        Texture logoTexture = new Texture(Gdx.files.internal("assets/etc/menu/logo.jpg"));
        logoImage = new Image(logoTexture);

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
        buttonRow.add(registerButton).width(150).pad(10);
        buttonRow.add(loginButton).width(150).pad(10);
        buttonRow.add(exitButton).width(150).pad(10);

        mainTable.add(buttonRow).center().row();

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // StardewValley.getGame().setScreen(new SignUpMenu());
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

