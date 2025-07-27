package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledMapRendererHelper {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;

    public TiledMapRendererHelper(String pathToTmx) {
        tiledMap = new TmxMapLoader().load(pathToTmx);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
        renderer.dispose();
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
}
