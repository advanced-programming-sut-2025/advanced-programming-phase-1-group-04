package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;


public class SignUpMenuScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final Texture backgroundTexture;
    private final Image backgroundImage;

    public SignUpMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = StardewValley.getSkin();
        backgroundTexture = new Texture(Gdx.files.internal("etc/menu/background_start.png"));
        backgroundImage = new Image(backgroundTexture);
    }

    @Override
    public void show() {
        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);
        stack.add(backgroundImage);

        Window window = new Window("Sign Up", skin);
        window.setMovable(false);
        window.setResizable(false);
        window.setSize(1200, 800);
        window.setPosition(
                (stage.getWidth() - window.getWidth()) / 2,
                (stage.getHeight() - window.getHeight()) / 2
        );

        Table contentTable = new Table();

        // ستون سمت چپ
        Table leftColumn = new Table();
        leftColumn.add(new Label("Username:", skin)).left().pad(5);
        leftColumn.row();
        TextField usernameField = new TextField("", skin);
        leftColumn.add(usernameField).width(350).pad(5,5,5,100);

        leftColumn.row();
        leftColumn.add(new Label("Password:", skin)).left().pad(5);
        leftColumn.row();
        TextField passwordField = new TextField("", skin);
//        passwordField.setPasswordCharacter('*');
//        passwordField.setPasswordMode(true);
        leftColumn.add(passwordField).width(350).pad(5,5,5,100);

        leftColumn.row();
        leftColumn.add(new Label("Confirm Pass:", skin)).left().pad(5);
        leftColumn.row();
        TextField confirmPassField = new TextField("", skin);
        leftColumn.add(confirmPassField).width(350).pad(5,5,5,100);

        leftColumn.row();
        leftColumn.add(new Label("Gender:", skin)).left().pad(5);
        leftColumn.row();


        SelectBox<String> genderBox = new SelectBox<>(skin);
        genderBox.setItems("female", "male", "prefer not to answer");
        leftColumn.add(genderBox).width(350).pad(5,5,5,100);


        // ستون سمت راست
        Table rightColumn = new Table();
        rightColumn.add(new Label("Nickname:", skin)).left().pad(5);
        rightColumn.row();
        TextField nicknameField = new TextField("", skin);
        rightColumn.add(nicknameField).width(350).pad(5);
        rightColumn.row();

        rightColumn.add(new Label(" ", skin)).pad(5); // فاصله برای تراز با password
        rightColumn.row();


        ImageButton diceButton = new ImageButton(skin);
        diceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: handle dice roll
            }
        });
        rightColumn.add(diceButton).width(50).height(50).pad(5);
        rightColumn.row();

        rightColumn.add(new Label("Email:", skin)).left().pad(25,5,5,5);
        rightColumn.row();
        TextField emailField = new TextField("", skin);
        rightColumn.add(emailField).width(350).pad(5);


        contentTable.add(leftColumn).top().pad(10);
        contentTable.add(rightColumn).top().pad(10);

        // دکمه‌های پایین
        Table buttonRow = new Table();
        TextButton backButton = new TextButton("Back", skin);
        TextButton nextButton = new TextButton("Next", skin);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                StardewValley.getGame().setScreen(new StartMenuScreen());
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: handle next
            }
        });

        buttonRow.add(backButton).width(150).pad(10);
        buttonRow.add(nextButton).width(150).pad(10);


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
        backgroundTexture.dispose();
    }
}
