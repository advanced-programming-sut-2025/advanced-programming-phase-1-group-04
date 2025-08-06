package io.Ap.StardewValley.Screen.PlayerScreen;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.EnumMap;
import java.util.Map;

public class BankPlayerAnimationFrames {
    private final Map<DirectionType, Animation<TextureRegion>> walkAnimations = new EnumMap<>(DirectionType.class);

    private final Map<DirectionType, TextureRegion> idleFrames = new EnumMap<>(DirectionType.class);

    private final Animation<TextureRegion> faintAnimation;
    private final Animation<TextureRegion> eatAnimation;

    private final Map<DirectionType, Animation<TextureRegion>> toolMilkPainAnimations = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, Animation<TextureRegion>> toolFishingPoleAnimations = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, Animation<TextureRegion>> toolScytheAnimations = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, Animation<TextureRegion>> toolShearAnimations = new EnumMap<>(DirectionType.class);

    public BankPlayerAnimationFrames(TextureRegion[][] sheet) {
        float frameDuration = 0.12f;

        faintAnimation = new Animation<>(frameDuration + 3f, sheet[0][0], sheet[2][4], sheet[0][4], sheet[0][5]);
        eatAnimation = new Animation<>(frameDuration, sheet[2][2]);

        walkAnimations.put(DirectionType.Down,
                new Animation<>(frameDuration, sheet[0][1], sheet[3][0], sheet[0][2], sheet[3][1]));
        idleFrames.put(DirectionType.Down, sheet[0][0]);

        walkAnimations.put(DirectionType.Up,
                new Animation<>(frameDuration, sheet[2][1], sheet[3][4], sheet[2][2], sheet[3][5]));
        idleFrames.put(DirectionType.Up, sheet[2][0]);

        // RIGHT
        TextureRegion[] rightFrames = new TextureRegion[] {
                sheet[3][3], sheet[2][5], sheet[1][0], sheet[3][2] , sheet[1][5], sheet[1][0]
        };
        walkAnimations.put(DirectionType.Right, new Animation<>(frameDuration, rightFrames));
        idleFrames.put(DirectionType.Right, sheet[1][0]);

        // LEFT = flipped RIGHT
        TextureRegion[] leftFrames = new TextureRegion[rightFrames.length];
        for (int i = 0; i < rightFrames.length; i++) {
            TextureRegion flipped = new TextureRegion(rightFrames[i]);
            flipped.flip(true, false);  // Flip horizontally
            leftFrames[i] = flipped;
        }
        TextureRegion idleLeft = new TextureRegion(sheet[1][0]);
        idleLeft.flip(true, false);
        walkAnimations.put(DirectionType.Left, new Animation<>(frameDuration, leftFrames));
        idleFrames.put(DirectionType.Left, idleLeft);

    }

    public Animation<TextureRegion> getAnimation(StateType type, DirectionType state) {
        return switch (type) {
            case Walk -> walkAnimations.get(state);
            case Eat -> eatAnimation;
            case Faint -> faintAnimation;
            default -> null;
        };
    }

    public TextureRegion getIdleFrame(DirectionType state) {
        return idleFrames.get(state);
    }
}
