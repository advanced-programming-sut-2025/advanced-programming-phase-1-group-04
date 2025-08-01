package io.Ap.StardewValley.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Model.Player.Player;
import io.Ap.StardewValley.Screen.GameScreen;
import io.Ap.StardewValley.Screen.PlayerScreen.DirectionType;
import io.Ap.StardewValley.Screen.PlayerScreen.PlayerRender;
import io.Ap.StardewValley.Screen.PlayerScreen.StateType;

public class GameScreenController {
    private final PlayerRender playerRender = new PlayerRender();
    private GameScreen view;

    public void setViews(GameScreen view) {
        this.view = view;
    }

    public void updateGame() {
        handleInputKey();
        playerRender.render();
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

        // clamp player inside map boundaries
        int playerWidth = (int) (16 * App.getGame().getPlayerScale());
        int playerHeight = (int) (32 * App.getGame().getPlayerScale());

        float mapWidth = 2688;
        float mapHeight = 2688;
        //newX = MathUtils.clamp(newX, 0, mapWidth - playerWidth);
        //newY = MathUtils.clamp(newY, playerHeight, mapHeight - playerHeight);

        // set position LibGdx, tile base
        Coordinate newCoordinate = getPlayerCoordinate(newX, newY);
        if (App.getGame().getTile(newCoordinate).isWalkable()) {
            player.setXLibGdx(newX);
            player.setYLibGdx(newY);
            player.setCoordinate(newCoordinate);
        }

        // status
        if (isMoving) {
            player.setState(StateType.Walk);
        } else if (isEating) {
            player.setState(StateType.Eat);
        } else {
            player.setState(StateType.Idle);
        }
        // ... TODO
    }

    public Coordinate getPlayerCoordinate(float xLibGdx, float yLibGdx) {
        final int tileSize = 16;

        // TODO: curren region:
        int mapHeightInTiles = App.getGame().getMap().getRegion(0, 0).getTiles().length;

        int logicX = mapHeightInTiles - 1 - (int)(yLibGdx / tileSize);
        int logicY = (int)(xLibGdx / tileSize);

        return new Coordinate(logicX, logicY);
    }


}
