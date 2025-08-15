package io.Ap.StardewValley.Screen.AnimalScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.Ap.StardewValley.Model.Animals.Animal;
import io.Ap.StardewValley.Model.Animals.AnimalType;
import io.Ap.StardewValley.Screen.PlayerScreen.DirectionType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BankAnimalTextures {
    private final static Map<AnimalType, TextureRegion> animalAvatars = new EnumMap<>(AnimalType.class);
    private final static Map<AnimalType, Animation<TextureRegion>> walkAnimations = new EnumMap<>(AnimalType.class);

    static {

    }

    public static TextureRegion getAvatar(AnimalType type) {
        return animalAvatars.get(type);
    }
}
