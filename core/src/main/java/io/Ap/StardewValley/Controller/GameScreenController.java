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

import java.io.FileNotFoundException;

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

        float newX = player.getCoordinate().getX();
        float newY = player.getCoordinate().getY();

        // Player move:
        if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveUp())){
            newY += player.getSpeed();
            isMoving = true;
            player.setDirection(DirectionType.Up);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveDown())){
            newY -= player.getSpeed();
            isMoving = true;
            player.setDirection(DirectionType.Down);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveRight())){
            newX += player.getSpeed();
            isMoving = true;
            player.setDirection(DirectionType.Right);
        } else if (Gdx.input.isKeyPressed(App.getKeyManager().getMoveLeft())){
            newX -= player.getSpeed();
            isMoving = true;
            player.setDirection(DirectionType.Left);
        }


        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getPauseGame()) && !view.isPaused()){
            view.setPaused(true);
            view.showPauseDialog();
        }

        // reload weapon:
        if (Gdx.input.isKeyJustPressed(App.getKeyManager().getReloadWeapon())){

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
        int playerWidth = 16;
        int playerHeight = 32;

        float mapWidth = 2688;
        float mapHeight = 2688;
        newX = MathUtils.clamp(newX, 0, mapWidth - playerWidth);
        newY = MathUtils.clamp(newY, playerHeight, mapHeight - playerHeight);

        // set position
        player.setCoordinate(new Coordinate((int) newX, (int) newY)); //TODO: coordinate int or float

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

    private void handleAnimationPlayer() {

    }
}
