package io.Ap.StardewValley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Screen.MenuScreen.MainMenuScreen;
import io.Ap.StardewValley.Controller.GameMenuController;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Screen.GameScreen;
import io.Ap.StardewValley.Screen.MapScreen.TiledMapScreen;
import io.Ap.StardewValley.Screen.MenuScreen.GameMenuScreen;
import io.Ap.StardewValley.Screen.MenuScreen.PreGameMenuScreen;
import io.Ap.StardewValley.Screen.MenuScreen.StartMenuScreen;

import java.io.FileNotFoundException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class StardewValley extends Game {
    private static StardewValley game;
    private static SpriteBatch batch;
    private static Skin skin;

    @Override
    public void create() {
        game = this;
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skin/NzSkin.json"));

        setCustomCursor();
        try {
            App.loadAppScreen();
        } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }

//        //TODO:
//        try {
//            App.loadApp();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        GameMenuController.newGame("Parsa", null, null);
//        GameMenuController.chooseMap(0 ,1);
//        GameMenuController.chooseMap(1 ,2);

//        getGame().setScreen(new PreGameMenuScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static StardewValley getGame() {
        return game;
    }

    public static void setGame(StardewValley game) {
        StardewValley.game = game;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        StardewValley.batch = batch;
    }

    public static Skin getSkin() {
        return skin;
    }

    private void setCustomCursor() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("etc/cursor.png"));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
    }
}
