package io.Ap.StardewValley.Screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.Controller.GameScreenController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Screen.CookingScreen.CookingStage;
import io.Ap.StardewValley.Screen.InventoryScreen.InventoryBar;
import io.Ap.StardewValley.Screen.InventoryScreen.InventoryStage;
import io.Ap.StardewValley.Screen.MapScreen.TiledMapRendererHelper;
import io.Ap.StardewValley.Screen.ShopScreen.BlackSmithScreen.BlackSmithMenu;
import io.Ap.StardewValley.StardewValley;

public class GameScreen implements Screen, InputProcessor {
    // Map:
    private TiledMapRendererHelper[][] mapRenderers = new TiledMapRendererHelper[3][3];
    private TiledMapRendererHelper currentMap;
    private final int[] farmSelections = new int[4];
    private static Image fullMap;

    private Stage stage;
    private final Table dialogTable = new Table();
    private final Table controllerTable = new Table();

    private OrthographicCamera camera;

    private boolean paused = false;
    private final GameScreenController controller = new GameScreenController();


    //inventory:
    private InventoryStage inventoryStage;
    private InventoryBar inventoryBar;

    //cooking:
    private CookingStage cookingStage = new CookingStage();

    //shops:
    private BlackSmithMenu blackSmithStage = new BlackSmithMenu();

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
        stage.addActor(controllerTable);

        Gdx.input.setInputProcessor(this);

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

        setFullMap();

        // initial inventory:
        inventoryStage = new InventoryStage();
        inventoryBar = new InventoryBar();

        // add processors
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(inventoryStage);
        multiplexer.addProcessor(cookingStage);
        multiplexer.addProcessor(blackSmithStage);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        // inventory bar:
        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);
        // ساخت جدول اصلی که سمت چپ اینونتوری و وسط محتوای پنجره رو بچینه
        Table mainLayout = new Table();
        mainLayout.setFillParent(true);
        ScrollPane inventoryScrollPane = inventoryBar.getInventoryScrollPane(); // تابع getInventoryScrollPane رو اضافه می‌کنی به کلاس Inventory
        mainLayout.add(inventoryScrollPane).width(130).height(800).pad(50, 100, 50, 0); // سمت چپ نوار
        mainLayout.add().expand(); // جای خالی برای window وسط
        stack.add(mainLayout);
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            // TODO:  current region:
            Coordinate cr = App.getGame().getMap().getCurrentRegionCoordinate();
            currentMap = mapRenderers[cr.getX()][cr.getY()];

            ScreenUtils.clear(0, 0, 0, 1);


            // update camera, controller table
            updateCamera();
            updateControllerTable();

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // render map
            SpriteBatch batch = StardewValley.getBatch();
            currentMap.renderBeforePlayer(camera);
            currentMap.renderDynamicBelowLayer(camera);

            // update game, render player
            batch.begin();
            controller.updatePlayer();
            batch.end();

            currentMap.renderDynamicAboveLayer(camera);
            currentMap.renderAfterPlayer(camera);

            controller.updateGame();
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void updateControllerTable() {
        Skin skin = StardewValley.getSkin();

        Coordinate cor = App.getGame().getCurrentPlayer().getCoordinate();
        controllerTable.clear();
        controllerTable.setFillParent(true);
        controllerTable.top().left();
        controllerTable.add(new Label("Player: (" + cor.getX() + ", " + cor.getY() + ")    " ,skin));
        //controllerTable.add(new Label("LibGdx: (" + App.getGame().getCurrentPlayer().getXLibGdx() + ", " + App.getGame().getCurrentPlayer().getYLibGdx() + ")" ,skin));
        controllerTable.add(new Label("Zoom: " + camera.zoom ,skin));
    }

    public void updateCamera() {
        float camHalfWidth = (camera.viewportWidth * camera.zoom) / 2f;
        float camHalfHeight = (camera.viewportHeight * camera.zoom) / 2f;


        float playerX = App.getGame().getCurrentPlayer().getXLibGdx();
        float playerY = App.getGame().getCurrentPlayer().getYLibGdx();

        float scale = App.getGame().getPlayerScale();
        float spriteWidth = 16 * scale;
        float spriteHeight = 32 * scale;

        float centerX = playerX + spriteWidth / 2f;
        float centerY = playerY + spriteHeight / 2f;

        int mapWidth = currentMap.getWidthPixels();
        int mapHeight = currentMap.getHeightPixels();

        centerX = MathUtils.clamp(centerX, camHalfWidth, mapWidth - camHalfWidth);
        centerY = MathUtils.clamp(centerY, camHalfHeight, mapHeight - camHalfHeight);

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
        reloadWeaponButton = new Label(Input.Keys.toString(App.getKeyManager().getZoom()), skin);
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

        //table.add(controller.getPlayerRender().getHeadImage()).size(16*6, 16*6);
        //table.add(getFullMap()).size(fullMap.getWidth() * 0.1f, fullMap.getHeight() * 0.1f);
        return table;
    }

    private void setFullMap() {
        Pixmap basePixmap = new Pixmap(Gdx.files.internal("etc/mapImages/Map.png"));
        Pixmap farm = new Pixmap(Gdx.files.internal("etc/mapImages/Farm1.png"));

        int mapWidth = basePixmap.getWidth(), mapHeight = basePixmap.getHeight();
        int farmWidth = farm.getWidth(), farmHeight = farm.getHeight();

        Pixmap combined = new Pixmap(mapWidth, mapHeight, Pixmap.Format.RGBA8888);

        combined.drawPixmap(basePixmap, 0, 0);
        combined.drawPixmap(new Pixmap(Gdx.files.internal("etc/mapImages/Farm" + farmSelections[0] +".png")), 0, 0);
        combined.drawPixmap(new Pixmap(Gdx.files.internal("etc/mapImages/Farm" + farmSelections[1] +".png")), mapWidth - farmWidth, 0);
        combined.drawPixmap(new Pixmap(Gdx.files.internal("etc/mapImages/Farm" + farmSelections[2] +".png")), mapWidth - farmWidth, mapHeight - farmHeight);
        combined.drawPixmap(new Pixmap(Gdx.files.internal("etc/mapImages/Farm" + farmSelections[3] +".png")), 0, mapHeight - farmHeight);

        Texture finalTexture = new Texture(combined);
        fullMap = new Image(finalTexture);

        basePixmap.dispose();
        farm.dispose();
        combined.dispose();
    }

    public static Image getFullMap() {
        return fullMap;
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
        if (Gdx.input.isKeyPressed(App.getKeyManager().getZoom())) {
            float zoomSpeed = 0.05f;
            camera.zoom += amountY * zoomSpeed;
            camera.zoom = MathUtils.clamp(camera.zoom, 0.05f, 0.6f);
            return true;
        }
        return false;
    }

    //getter and setters:

    public InventoryStage getInventoryStage() {
        return inventoryStage;
    }

    public InventoryBar getInventoryBar() {
        return inventoryBar;
    }

    public CookingStage getCookingStage() {
        return cookingStage;
    }

    public BlackSmithMenu getBlackSmithStage() {
        return blackSmithStage;
    }
}