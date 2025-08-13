package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Player.Player;
import io.Ap.StardewValley.Server.Client;
import io.Ap.StardewValley.Server.Server;
import io.Ap.StardewValley.StardewValley;

import java.util.ArrayList;

public class Co_opHostScreen implements Screen {

    private final Stage stage;
    private final Table mainTable;

    private final TextButton StartButton, backButton;
    private final Image backgroundImage;
    private final ArrayList<String> players;
    private final CheckBox visibilityCheckbox;
    private final Server server;
    private final Window window = new Window("", StardewValley.getSkin());

    private final Array<Animation<TextureRegion>> butterflyAnimations;

    public Co_opHostScreen(String hostName) {
        players = new ArrayList<>();
        server = new Server(hostName , true);
        try{
            server.addClient(new Client(server.getIPv4Address(),
                    server.getUdpPort(), App.getCurrentUser().getId(), App.getCurrentUser().getUsername()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        butterflyAnimations = new Array<>();
        Skin skin = StardewValley.getSkin();
        visibilityCheckbox = new CheckBox("   Public" , skin);
        visibilityCheckbox.setChecked(true);
        StartButton = new TextButton("Start", skin , "Chicken");
        backButton = new TextButton("Back", skin, "Plant");
        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_start.png")));
        mainTable = new Table();
        stage = new Stage(new ScreenViewport());

        Texture sheet = new Texture(Gdx.files.internal("etc/gogoli/companions.png"));
        TextureRegion[][] tmp = TextureRegion.split(sheet, 16, 16);

        for (int i = 0; i < 4; i++) {
            TextureRegion[] frames = new TextureRegion[4];
            System.arraycopy(tmp[0], 4 * i, frames, 0, 4);
            butterflyAnimations.add(new Animation<>(0.13f, frames));
        }
        players.add("Player1: " + App.getCurrentUser().getUsername());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);

        stack.add(backgroundImage);
        stack.add(mainTable);

        mainTable.setFillParent(true);
        mainTable.center().top().padTop(100);

        int numOfButterfly = 10;
        for (int i = 0; i < numOfButterfly; i++) {
            Animation<TextureRegion> baseAnimation = butterflyAnimations.random();
            TextureRegion[] originalFrames = baseAnimation.getKeyFrames();
            TextureRegion[] flippedFrames = new TextureRegion[originalFrames.length];

            boolean shouldFlip = MathUtils.randomBoolean(0.5f);

            for (int j = 0; j < originalFrames.length; j++) {
                flippedFrames[j] = new TextureRegion(originalFrames[j]);
                if (shouldFlip) {
                    flippedFrames[j].flip(true, false);
                }
            }

            Animation<TextureRegion> finalAnimation = new Animation<>(baseAnimation.getFrameDuration(), flippedFrames);

            float x = MathUtils.random(0, Gdx.graphics.getWidth());
            float y = MathUtils.random(0, Gdx.graphics.getHeight());
            float scale = MathUtils.random(2f, 5.5f);

            animationActor butterfly = new animationActor(
                    finalAnimation,
                    x,
                    y,
                    scale,
                    animationActor.MovementType.Random
            );

            stage.addActor(butterfly);
        }

        Table buttonRow = new Table();
        buttonRow.add(StartButton).width(240).pad(10);
        buttonRow.add(backButton).width(240).row();
        buttonRow.add(visibilityCheckbox).width(100);

        mainTable.add(buttonRow).center().row();

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                StardewValley.getGame().setScreen(new Co_opScreen());
            }
        });


        updatePlayers();
        stage.addActor(window);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
        updatePlayers();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void refreshList () {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            updatePlayers();
        }
    }

    private void updatePlayers () {
        window.clear();
        for (Client client : server.getClients()) {
            Label titleLabel = new Label("Players: " + server.getHostName(), StardewValley.getSkin(), "Bold");
            titleLabel.setAlignment(Align.center);
            window.getTitleTable().clear();
            window.getTitleTable().add(titleLabel).expandX().center().padTop(5).padBottom(10);

            window.setMovable(false);
            window.setResizable(false);
            window.setSize(900, 600);
            window.setPosition(
                    (stage.getWidth() - window.getWidth()) / 2,
                    (stage.getHeight() - window.getHeight()) / 2 - 200
            );
            window.add(new Label(client.getPlayerName(), StardewValley.getSkin()));
        }
    }
}
