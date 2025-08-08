package io.Ap.StardewValley.Controller;

import com.badlogic.gdx.Gdx;
import io.Ap.StardewValley.Controller.SirkBozorg.NightController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Model.Plants.Crop;
import io.Ap.StardewValley.Model.Plants.CropType;
import io.Ap.StardewValley.Model.Player.Player;
import io.Ap.StardewValley.Model.Player.Skill;
import io.Ap.StardewValley.Model.Time.DateAndTime;
import io.Ap.StardewValley.Model.Time.Season;
import io.Ap.StardewValley.Screen.GameScreen;
import io.Ap.StardewValley.Screen.MapScreen.DynamicMapLayerRender;
import io.Ap.StardewValley.Screen.MapScreen.RegionTransition;
import io.Ap.StardewValley.Screen.PlayerScreen.DirectionType;
import io.Ap.StardewValley.Screen.PlayerScreen.PlayerRender;
import io.Ap.StardewValley.Screen.PlayerScreen.StateType;

import java.util.concurrent.atomic.AtomicBoolean;

public class GameScreenController {
    private final PlayerRender playerRender = new PlayerRender();
    private GameScreen view;

    //inventory:
    private boolean isInventoryStageVisible = false;
    private boolean inventoryStageNeedsUpdate = false;

    //cooking:
    private boolean isCookingStageVisible = false;
    private boolean cookingStageNeedsUpdate = false;

    public void setViews(GameScreen view) {
        this.view = view;
    }

    public void updateTime(float delta) {
        DateAndTime time = App.getGame().getCurrentTime();
        int oldHour = time.getHour();

        time.update(delta);

        int newHour = time.getHour();

        if (oldHour <= 24 && newHour >= 24)
            goToNextDay();

        view.getTimeBar().updateTime();
        view.getTimeBar().updateEnergy();
    }

    public void goToNextDay() {
        view.setPaused(true);
        view.showNightOverlay(() -> {
            Season oldSeason = App.getGame().getCurrentTime().getSeason();
            NightController.nightControl();
            Season newSeason = App.getGame().getCurrentTime().getSeason();
            if (oldSeason != newSeason) {
                view.updateSeasonMap(newSeason.name().toLowerCase());
                view.getTimeBar().updateSeason(newSeason);
            }
            // time bar:
            view.setWeatherLayerToStage(App.getGame().getCurrentTime().getWeather());
            view.getTimeBar().updateWeather();
            view.setPaused(false);
        });
        view.getTimeBar().updateTime();
    }

    public void  updateGame() {
        handleInputKey();

        //inventory bar:
        view.getInventoryBar().updateInventoryBar();

        //inventory stage:
        view.getInventoryStage().setVisibleAll(isInventoryStageVisible);
        if (inventoryStageNeedsUpdate) {
            view.getInventoryStage().update();
            inventoryStageNeedsUpdate = false;
        }

        //cooking stage
        view.getCookingStage().setVisibleAll(isCookingStageVisible);
        if (cookingStageNeedsUpdate) {
            view.getCookingStage().update();
            cookingStageNeedsUpdate = false;
        }
    }

    private void handleInputKey(){
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getPauseGame()) && !view.isPaused()){
            view.setPaused(true);
            view.showPauseDialog();
        }

        // cheats:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getCheatTime())){

        }
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getCheatLevel())){

        }
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getCheatLife())){

        }
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getCheatHp())){

        }
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getCheatBossFight())){

        }

        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getAynazCheat())) {
            App.getGame().getCurrentPlayer().addAbility(Skill.Farming, 10);
            App.getGame().getCurrentPlayer().setInventoryCapacity(24);
            App.getGame().getCurrentPlayer().getInventory().addItem(new Crop(CropType.Potato));
            inventoryStageNeedsUpdate = true;
            cookingStageNeedsUpdate = true;
        }

        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getNafisehCheat())){
            //App.getGame().getCurrentPlayer().setEnergy(-1);
//            App.getGame().getCurrentTime().addHour(14);
//            App.getGame().getCurrentTime().setMinute(55);
            //App.getGame().getCurrentTime().addDay(27);
            //goToNextDay();
            //App.getGame().getMap().getFullMap()[App.getGame().getCurrentPlayer().getCoordinate().getX()][App.getGame().getCurrentPlayer().getCoordinate().getY()].setFertilize(1);
            App.getGame().getMap().getFullMap()
                    [App.getGame().getCurrentPlayer().getCoordinate().getX()][App.getGame().getCurrentPlayer().getCoordinate().getY()]
                    .setPlowed(true);
        }

        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getNafisehCheatTime())){
            //App.getGame().getCurrentTime().addHour(1);
            App.getGame().getMap().getFullMap()
                    [App.getGame().getCurrentPlayer().getCoordinate().getX()][App.getGame().getCurrentPlayer().getCoordinate().getY()]
                    .setWatered(true);
        }

        //inventory:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getOpenInventory())){
            isInventoryStageVisible = !isInventoryStageVisible;
        }
        //cooking:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getOpenRefrigerator())){
            isCookingStageVisible = !isCookingStageVisible;
        }
    }

    public void updatePlayer() {
        // handle energy:
        Player player = App.getGame().getCurrentPlayer();
        if (player.getEnergy() > 0) {
            handlePlayerInputKey();
        } else {
            player.setState(StateType.Faint);
            player.setDirection(DirectionType.Down);
        }

        playerRender.render();
    }

    private void handlePlayerInputKey() {
        // player inputs: walk, eat, use tool:
        Player player = App.getGame().getCurrentPlayer();
        boolean isMoving = false;
        boolean isUsingTool = false;
        boolean isEating = false;

        float newX = player.getXLibGdx();
        float newY = player.getYLibGdx();

        // Player move:
        int speed = App.getGame().getPlayerSpeed();
        if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveUp())){
            newY += speed;
            isMoving = true;
            player.setDirection(DirectionType.Up);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveDown())){
            newY -= speed;
            isMoving = true;
            player.setDirection(DirectionType.Down);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveRight())){
            newX += speed;
            isMoving = true;
            player.setDirection(DirectionType.Right);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveLeft())){
            newX -= speed;
            isMoving = true;
            player.setDirection(DirectionType.Left);
        }

        // status
        if (isMoving) {
            player.setState(StateType.Walk);
            //player.addEnergy(-1);
        } else if (isEating) {
            player.setState(StateType.Eat);
        } else {
            player.setState(StateType.Idle);
        }

        // change region:
        Coordinate oldRegion = App.getGame().getMap().getCurrentRegionCoordinate();
        Coordinate newCoordinate = getPlayerCoordinate(newX, newY);

        // set new coordinate:
        if (App.getGame().getTile(newCoordinate) != null && App.getGame().getTile(newCoordinate).isWalkable()) {
            // calculate newRegion:
            player.setXLibGdx(newX);
            player.setYLibGdx(newY);
            player.setCoordinate(newCoordinate);

            Coordinate newRegion = App.getGame().getMap().getCurrentRegionCoordinate(newCoordinate);
            if (!oldRegion.equals(newRegion)) {
                RegionTransition rt = RegionTransition.get(oldRegion, newRegion);
                if (rt != null) {
                    player.setXLibGdx(rt.getLibGdxX(16));
                    player.setYLibGdx(rt.getLibGdxY(16));
                    player.setCoordinate(rt.getDestinationCoordinate());
                }
            }
        }
    }

    public Coordinate getPlayerCoordinate(float xLibGdx, float yLibGdx) {
        final int tileSize = 16;

        // TODO: curren region:
        Coordinate cor = App.getGame().getMap().getCurrentRegionCoordinate();
        int mapHeightInTiles = App.getGame().getMap().getCurrentRegion().getTiles().length;

        int[] rowOffsets = App.getGame().getMap().getRowOffsets();
        int[] colOffsets = App.getGame().getMap().getColOffsets();


        int localX = mapHeightInTiles - 1 - (int)(yLibGdx / tileSize);
        int localY = (int)((xLibGdx + tileSize / 2f) / tileSize);

        int globalX = rowOffsets[cor.getX()] + localX;
        int globalY = colOffsets[cor.getY()] + localY;

        return new Coordinate(globalX, globalY);
    }
}
