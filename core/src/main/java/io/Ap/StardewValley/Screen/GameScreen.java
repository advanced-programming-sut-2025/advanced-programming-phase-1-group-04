package io.Ap.StardewValley.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Controller.GameMenuController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Screen.MenuScreen.StartMenuScreen;
import io.Ap.StardewValley.StardewValley;

import java.io.IOException;

public class GameScreen implements Screen, InputProcessor {
    private Stage stage;
    private Table table = new Table();

    private final float MapWidth = 2688;
    private final float MapHeight = 2688;

    private boolean paused = false;

    private OrthographicCamera camera;
    private GameMenuController controller;

    public GameScreen() {
        //this.controller = controller;
        //controller.setViews(this);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Table getTable() {
        return table;
    }

    public Stage getStage() {
        return stage;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    @Override
    public void show() {
        //stage = new Stage(new ScreenViewport(), App.getSharedBatch());
        stage = new Stage(new ScreenViewport());
        table.setFillParent(true);
        table.top().left();
        stage.addActor(table);
        Gdx.input.setInputProcessor(this);

        // InputMultiplexer for resume menu
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        // set camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            ScreenUtils.clear(0, 0, 0, 1);

            //App.getGame().updateTime(delta);

            // update camera
            updateCamera();

            // set shader
            //TillDown.getBatch().setShader(App.getShader());
            //App.getShader().setUniformi("u_grayscale", App.isGrayscale() ? 1 : 0);

            // update game
            StardewValley.getBatch().begin();
            //controller.updateGame();
            StardewValley.getBatch().end();
        }

        //App.getShader().setUniformi("u_grayscale", App.isGrayscale() ? 1 : 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void updateCamera() {
        float camHalfWidth = camera.viewportWidth / 2f;
        float camHalfHeight = camera.viewportHeight / 2f;

//        float playerX = App.getGame().getPlayer().getX();
//        float playerY = App.getGame().getPlayer().getY();
//        float spriteWidth = App.getGame().getPlayer().getHero().getSprite().getWidth();
//        float spriteHeight = App.getGame().getPlayer().getHero().getSprite().getHeight();

        float centerX = 10 + 20 / 2f;
        float centerY = 234 + 32 / 2f;

        centerX = MathUtils.clamp(centerX, camHalfWidth, MapWidth - camHalfWidth);
        centerY = MathUtils.clamp(centerY, camHalfHeight, MapHeight - camHalfHeight);

        camera.position.set(centerX, centerY, 0);
        camera.update();

        StardewValley.getBatch().setProjectionMatrix(camera.combined);
    }

    public void showPauseDialog() {
        Skin skin = StardewValley.getSkin();
        Table table = getTableDialog();

//        CheckBox colorCheckbox = new CheckBox(" " + App.getLanguage("setting.color"), skin);
//        colorCheckbox.setChecked(App.isGrayscale());
//
//        CheckBox soundCheckbox = new CheckBox(" " + App.getLanguage("setting.sound"), skin);
//        soundCheckbox.setChecked(App.isIsSfx());
//
//        CheckBox autoReloadCheckbox = new CheckBox(" " + App.getLanguage("setting.autoReload"), skin);
//        autoReloadCheckbox.setChecked(App.isIsAutoReload());
//
//        table.row();
//        table.add(colorCheckbox).pad(10);
//        table.add(soundCheckbox).pad(10);
//        table.add(autoReloadCheckbox).pad(10).row();

        Dialog pauseDialog = new Dialog("Pause", skin, "round") {
            @Override
            protected void result(Object object) {
                if (object instanceof String) {
                    switch ((String) object) {
                        case "resume":
                            //GameController.setSetting(colorCheckbox.isChecked(), soundCheckbox.isChecked(), autoReloadCheckbox.isChecked());
                            paused = false;
                            break;
                        case "give up":
                            //StardewValley.getGame().setScreen(new EndGameMenu(0));
                            break;
                        case "save & exit":
                            //GameController.saveGame();
                            StardewValley.getGame().setScreen(new StartMenuScreen());
                            break;
                    }
                }
            }
        };

        pauseDialog.getContentTable().add(table).expand().fill();

        pauseDialog.button("resume", "resume").row();
        pauseDialog.button("give up", "give up").row();
        pauseDialog.button("save & exit", "save & exit").row();

        pauseDialog.setMovable(false);
        pauseDialog.setModal(true);
        pauseDialog.show(stage);
    }

    private Table getTableDialog() {
        final Label moveUp, moveDown, moveLeft, moveRight, autoAim, reloadWeapon, cheatTime, cheatLevel, cheatLife, cheatHp, pauseGame, shash;
        final Label moveUpButton, moveDownButton, moveLeftButton, moveRightButton, autoAimButton,pauseGameButton,  reloadWeaponButton, cheatTimeButton, cheatLevelButton, cheatLifeButton, cheatHpButton;

        Skin skin = StardewValley.getSkin();

        shash = new Label("     ", skin);

        moveUp = new Label("up", skin);
        moveDown = new Label("down", skin);
        moveLeft = new Label("left", skin);
        moveRight = new Label("right", skin);
        autoAim = new Label("autoAim", skin);
        reloadWeapon = new Label("reload", skin);
        cheatTime = new Label("cheatTime", skin);
        cheatLevel = new Label("cheatLevel", skin);
        cheatLife = new Label("cheatLife", skin);
        cheatHp = new Label("cheatHP", skin);
        pauseGame = new Label("pause", skin);

        float labelFontScale = 1.5f;

        moveUpButton = new Label(Input.Keys.toString(App.getKeyManager().getMoveUp()), skin);
        moveDownButton = new Label(Input.Keys.toString(App.getKeyManager().getMoveDown()), skin);
        moveLeftButton = new Label(Input.Keys.toString(App.getKeyManager().getMoveLeft()), skin);
        moveRightButton = new Label(Input.Keys.toString(App.getKeyManager().getMoveRight()), skin);
        autoAimButton = new Label(Input.Keys.toString(App.getKeyManager().getCheatBossFight()), skin);
        reloadWeaponButton = new Label(Input.Keys.toString(App.getKeyManager().getReloadWeapon()), skin);
        cheatTimeButton = new Label(Input.Keys.toString(App.getKeyManager().getCheatTime()), skin);
        cheatLevelButton = new Label(Input.Keys.toString(App.getKeyManager().getCheatLevel()), skin);
        cheatLifeButton = new Label(Input.Keys.toString(App.getKeyManager().getCheatLife()), skin);
        cheatHpButton = new Label(Input.Keys.toString(App.getKeyManager().getCheatHp()), skin);
        pauseGameButton = new Label(Input.Keys.toString(App.getKeyManager().getPauseGame()), skin);


        moveUpButton.setFontScale(labelFontScale);
        moveDownButton.setFontScale(labelFontScale);
        moveLeftButton.setFontScale(labelFontScale);
        moveRightButton.setFontScale(labelFontScale);
        autoAimButton.setFontScale(labelFontScale);
        reloadWeaponButton.setFontScale(labelFontScale);
        cheatTimeButton.setFontScale(labelFontScale);
        cheatLevelButton.setFontScale(labelFontScale);
        cheatLifeButton.setFontScale(labelFontScale);
        cheatHpButton.setFontScale(labelFontScale);
        pauseGameButton.setFontScale(labelFontScale);


        Table table = new Table();
        table.add(moveUp);
        table.add(moveUpButton).width(300).pad(10);
        table.add(cheatTime);
        table.add(cheatTimeButton).width(300).pad(10).row();

        table.add(moveDown);
        table.add(moveDownButton).width(325).pad(10);
        table.add(cheatLevel);
        table.add(cheatLevelButton).width(325).pad(10).row();

        table.add(moveLeft);
        table.add(moveLeftButton).width(325).pad(10);
        table.add(cheatLife);
        table.add(cheatLifeButton).width(325).pad(10).row();

        table.add(moveRight);
        table.add(moveRightButton).width(325).pad(10);
        table.add(cheatHp);
        table.add(cheatHpButton).width(325).pad(10).row();

        table.add(autoAim);
        table.add(autoAimButton).width(325).pad(10);
        table.add(pauseGame);
        table.add(pauseGameButton).width(325).pad(10).row();

        table.add(reloadWeapon);
        table.add(reloadWeaponButton).width(325).pad(10);
        table.add(shash);

        return table;
    }




    @Override
    public void resize(int width, int height) {

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //controller.getPlayerController().getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //controller.getPlayerController().getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}