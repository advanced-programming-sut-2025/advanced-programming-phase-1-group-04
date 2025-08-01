package io.Ap.StardewValley.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Controller.GameScreenController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Screen.MapScreen.TiledMapRendererHelper;
import io.Ap.StardewValley.StardewValley;

public class GameScreen implements Screen, InputProcessor {
    // Map:
    private TiledMapRendererHelper[][] mapRenderers = new TiledMapRendererHelper[3][3];
    private final int[] farmSelections = new int[4];

    private Stage stage;
    private final Table dialogTable = new Table();
    private final Table controllerTable = new Table();

    private OrthographicCamera camera;

    private boolean paused = false;
    private final GameScreenController controller = new GameScreenController();

    public GameScreen(int[] farmSelections) {
        System.arraycopy(farmSelections, 0, this.farmSelections, 0, 4);
        controller.setViews(this);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Table getDialogTable() {
        return dialogTable;
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
        stage = new Stage(new ScreenViewport());
        dialogTable.setFillParent(true);
        dialogTable.top().left();
        stage.addActor(dialogTable);
        Gdx.input.setInputProcessor(this);

        // InputMultiplexer for resume menu
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        // set camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 0.2f;


        // set MapRenderers:
        mapRenderers[0][0] = new TiledMapRendererHelper("Farm" + farmSelections[0]);
        mapRenderers[0][1] = new TiledMapRendererHelper("path1");
        mapRenderers[0][2] = new TiledMapRendererHelper("Farm" + farmSelections[1]);

        mapRenderers[1][0] = new TiledMapRendererHelper("path4");
        mapRenderers[1][1] = new TiledMapRendererHelper("Town");
        mapRenderers[1][2] = new TiledMapRendererHelper("path2");

        mapRenderers[2][0] = new TiledMapRendererHelper("Farm" + farmSelections[3]);
        mapRenderers[2][1] = new TiledMapRendererHelper("path3");
        mapRenderers[2][2] = new TiledMapRendererHelper("Farm" + farmSelections[2]);
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            // TODO:  current region:
            Coordinate cr = App.getGame().getMap().getCurrentRegionCoordinate();
            TiledMapRendererHelper currentMap = mapRenderers[cr.getX()][cr.getY()];

            ScreenUtils.clear(0, 0, 0, 1);


            // update camera, controller table
            updateCamera();
            updateControllerTable();

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // render map
            SpriteBatch batch = StardewValley.getBatch();
            currentMap.renderBeforePlayer(camera);

            // update game, render player
            batch.begin();
            controller.updateGame();
            batch.end();

            currentMap.renderAfterPlayer(camera);
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.addActor(controllerTable);
        stage.draw();
    }

    private void updateControllerTable() {
        Skin skin = StardewValley.getSkin();

        Coordinate cor = App.getGame().getCurrentPlayer().getCoordinate();
        controllerTable.clear();
        controllerTable.setFillParent(true);
        controllerTable.top().left();
        controllerTable.add(new Label("Player: (" + cor.getX() + ", " + cor.getY() + ")" ,skin));
        controllerTable.add(new Label("LibGdx: (" + App.getGame().getCurrentPlayer().getXLibGdx() + ", " + App.getGame().getCurrentPlayer().getYLibGdx() + ")" ,skin));
    }


    private void updateCamera() {
        float playerX = App.getGame().getCurrentPlayer().getXLibGdx();
        float playerY = App.getGame().getCurrentPlayer().getYLibGdx();

        float scale = App.getGame().getPlayerScale();
        float spriteWidth = 16 * scale;
        float spriteHeight = 32 * scale;

        float centerX = playerX + spriteWidth / 2f;
        float centerY = playerY + spriteHeight / 2f;

        camera.position.set(centerX, centerY, 0);
        camera.update();

        StardewValley.getBatch().setProjectionMatrix(camera.combined);
    }


    public void showPauseDialog() {
        Skin skin = StardewValley.getSkin();
        Table table = getTableDialog();
        Dialog pauseDialog = new Dialog("Pause", skin) {
            @Override
            protected void result(Object object) {
                if (object instanceof String) {
                    switch ((String) object) {
                        case "resume":
                            paused = false;
                            break;
                        case "exit":
                            Gdx.app.exit();
                            break;
                    }
                }
            }
        };

        pauseDialog.getContentTable().add(table).expand().fill();

        pauseDialog.button("resume", "resume").row();
        pauseDialog.button("exit", "exit").row();

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
        for (TiledMapRendererHelper[] tiledMapRenderers: mapRenderers) {
            for (TiledMapRendererHelper tmp: tiledMapRenderers) {
                tmp.dispose();
            }
        }
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