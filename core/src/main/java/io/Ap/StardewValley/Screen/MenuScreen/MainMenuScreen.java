package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;

public class MainMenuScreen implements Screen {
    private final Stage stage;
    private final Table mainTable;

    private final TextButton profileButton, gameButton, exitButton, logoutButton;
    private final Image backgroundImage, logoImage;

    private final Animation<TextureRegion> butterflyAnimation;

    public MainMenuScreen() {
        Skin skin = StardewValley.getSkin();

        profileButton = new TextButton("Profile", skin, "Chicken");
        gameButton = new TextButton("Game", skin, "Strawberry");
        logoutButton = new TextButton("Logout", skin, "Earth");
        exitButton = new TextButton("Exit", skin, "Plant");

        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_chill.png")));
        logoImage = new Image(new Texture(Gdx.files.internal("etc/menu/logo.png")));

//        Texture birdSheet = new Texture(Gdx.files.internal("etc/gogoli/animations.png"));
//        TextureRegion[][] tmp = TextureRegion.split(birdSheet, 64, 64);
//        TextureRegion[] birdFrames = new TextureRegion[8];
//        System.arraycopy(tmp[17], 0, birdFrames, 0, 8);

        Texture birdSheet = new Texture(Gdx.files.internal("etc/gogoli/companions.png"));
        TextureRegion[][] tmp = TextureRegion.split(birdSheet, 16, 16);
        TextureRegion[] birdFrames = new TextureRegion[4];
        System.arraycopy(tmp[9], 0, birdFrames, 0, 4);

        butterflyAnimation = new Animation<>(0.13f, birdFrames);

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

//        Group cloudLayer = new Group();
//        stack.add(cloudLayer);

        stack.add(mainTable);

        mainTable.setFillParent(true);
        mainTable.center().top().padTop(100);

        int numOfButterfly = 10;
        for (int i = 0; i < numOfButterfly; i++) {
            animationActor animation = new animationActor(butterflyAnimation, 1920, 375, 4f, animationActor.MovementType.Random);
            stage.addActor(animation);
        }

        mainTable.add(logoImage).center().padBottom(50).row();

        Table buttonRow = new Table();
        buttonRow.add(profileButton).width(240).pad(10);
        buttonRow.add(gameButton).width(240).pad(10);
        buttonRow.add(logoutButton).width(240).pad(10);
        buttonRow.add(exitButton).width(240).pad(10);
        mainTable.add(buttonRow).center().row();


//        Random random = new Random();
//        int numberOfClouds = 8;
//        for (int i = 0; i < numberOfClouds; i++) {
//            Texture cloudTex = cloudTextures.random();
//
//            float startX = random.nextFloat() * Gdx.graphics.getWidth();
//            float startY = 360 + random.nextFloat() * Gdx.graphics.getHeight();
//            float speed = 5 + random.nextFloat() * 15;
//            float scaleFactor = 1.5f + random.nextFloat() * 0.8f;
//
//            CloudActor cloud = new CloudActor(cloudTex, speed, startX, startY, scaleFactor);
//            cloudActors.add(cloud);
//            cloudLayer.addActor(cloud);
//        }

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new Shash());
            }
        });

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // StardewValley.getGame().setScreen(new LoginMenu());
            }
        });

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                StardewValley.getGame().setScreen(new StartMenuScreen());
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