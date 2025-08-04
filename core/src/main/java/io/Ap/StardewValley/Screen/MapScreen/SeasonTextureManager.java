package io.Ap.StardewValley.Screen.MapScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class SeasonTextureManager {
    private static final Map<String, Texture> sharedTextures = new HashMap<>();

    public static Texture getSeasonTexture(String originalPath, String season) {
        String newPath = originalPath
                .replace("spring", season)
                .replace("summer", season)
                .replace("winter", season);

        if (sharedTextures.containsKey(newPath)) {
            return sharedTextures.get(newPath);
        }

        Texture t = new Texture(Gdx.files.internal(newPath));
        sharedTextures.put(newPath, t);
        return t;
    }

    public static void disposeAll() {
        for (Texture t : sharedTextures.values()) {
            t.dispose();
        }
        sharedTextures.clear();
    }
}
