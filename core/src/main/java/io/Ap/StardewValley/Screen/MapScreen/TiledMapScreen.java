package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledMapScreen implements Screen {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    @Override
    public void show() {
        // بارگذاری نقشه
        tiledMap = new TmxMapLoader().load("map/tiled-maps-master/level25.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        // دوربین
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600); // اندازه صفحه
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }
}
