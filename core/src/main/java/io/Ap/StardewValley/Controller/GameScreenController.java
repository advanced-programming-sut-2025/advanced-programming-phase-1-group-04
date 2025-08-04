package io.Ap.StardewValley.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.Ap.StardewValley.Model.Animals.AnimalProduct;
import io.Ap.StardewValley.Model.Animals.AnimalProductType;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Model.Plants.Crop;
import io.Ap.StardewValley.Model.Plants.CropType;
import io.Ap.StardewValley.Model.Player.Player;
import io.Ap.StardewValley.Model.Player.Skill;
import io.Ap.StardewValley.Screen.GameScreen;
import io.Ap.StardewValley.Screen.MapScreen.RegionTransition;
import io.Ap.StardewValley.Screen.PlayerScreen.DirectionType;
import io.Ap.StardewValley.Screen.PlayerScreen.PlayerRender;
import io.Ap.StardewValley.Screen.PlayerScreen.StateType;

public class GameScreenController {
    private final PlayerRender playerRender = new PlayerRender();
    private GameScreen view;

    //inventory:
    private static boolean isInventoryStageVisible = false;
    private static boolean inventoryStageNeedsUpdate = false;

    //cooking:
    private static boolean isCookingStageVisible = false;
    private static boolean cookingStageNeedsUpdate = false;

    public void setViews(GameScreen view) {
        this.view = view;
    }

    public void  updateGame() {
        handleInputKey();
        playerRender.render();

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
            App.getGame().getCurrentPlayer().getInventory().addItem(new AnimalProduct(AnimalProductType.Egg), 10);
            inventoryStageNeedsUpdate = true;
            cookingStageNeedsUpdate = true;
        }

        //inventory:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getOpenInventory())){
            isInventoryStageVisible = !isInventoryStageVisible;
            if (isInventoryStageVisible) {
                inventoryStageNeedsUpdate = true;
                isCookingStageVisible = false;
            }
        }

        //cooking:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getOpenRefrigerator())){
            isCookingStageVisible = !isCookingStageVisible;
            if (isCookingStageVisible) {
                cookingStageNeedsUpdate = true;
                isInventoryStageVisible = false;
            }
        }

        // status
        if (isMoving) {
            player.setState(StateType.Walk);
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


    public Image getPlayerHeadImage() {
        return playerRender.getHeadImage();
    }


    public static void setInventoryStageNeedsUpdate(boolean inventoryStageNeedsUpdate) {
        GameScreenController.inventoryStageNeedsUpdate = inventoryStageNeedsUpdate;
    }

    public static void setCookingStageNeedsUpdate(boolean cookingStageNeedsUpdate) {
        GameScreenController.cookingStageNeedsUpdate = cookingStageNeedsUpdate;
    }
}
