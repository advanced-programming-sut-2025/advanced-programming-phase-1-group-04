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

public class StartMenuScreen implements Screen {
    private Stage stage;
    private final Table table;

    private final TextButton signUpButton, loginButton, guestButton, exitButton;

    public StartMenuScreen() {
        Skin skin = StardewValley.getSkin();

        signUpButton = new TextButton("signup", skin);
        loginButton = new TextButton("login", skin);
        guestButton = new TextButton("guest", skin);
        exitButton = new TextButton("exit", skin);

        table = new Table();
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        table.center().row();

        table.add(signUpButton).width(200).pad(10).row();
        table.add(loginButton).width(200).pad(10).row();
        table.add(guestButton).width(200).pad(10).row();
        table.add(exitButton).width(150).pad(10);

        stage.addActor(table);

        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new SignUpMenu());
            }
        });
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new LoginMenu());
            }
        });
        guestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StartMenuController.guestMode();
                //StardewValley.getGame().setScreen(new MainMenu());
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
