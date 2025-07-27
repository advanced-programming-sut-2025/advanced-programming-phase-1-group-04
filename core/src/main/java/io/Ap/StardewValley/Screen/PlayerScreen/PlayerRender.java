package io.Ap.StardewValley.Screen.PlayerScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.StardewValley;

import java.util.EnumMap;
import java.util.Map;

public class PlayerRender {
    private float stateTime = 0f;

    private PlayerAnimationFrames bodyAnimations;
    private PlayerAnimationFrames hand01Animations;
    private PlayerAnimationFrames hand02Animations;
    private PlayerAnimationFrames pantAnimations;

    private final Map<DirectionType, TextureRegion> hairFrames = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, TextureRegion> shirtFrames = new EnumMap<>(DirectionType.class);

    public PlayerRender() {
        int pantIndex = App.getGame().getCurrentPlayer().getPantIndex();
        int shirtIndex = App.getGame().getCurrentPlayer().getShirtIndex();
        int hairIndex = App.getGame().getCurrentPlayer().getHairIndex();

        // HairStyle
        TextureRegion[][] hairSheet = TextureRegion.split(new Texture("player/clothes/hairstyles.png"), 16, 32);
        hairFrames.put(DirectionType.Down, hairSheet[(hairIndex / 8) * 3][hairIndex % 8]);
        hairFrames.put(DirectionType.Up, hairSheet[(hairIndex / 8) * 3 + 2][hairIndex % 8]);
        hairFrames.put(DirectionType.Right, hairSheet[(hairIndex / 8) * 3 + 1][hairIndex % 8]);
        TextureRegion leftHair = new TextureRegion(hairSheet[(hairIndex / 8) * 3 + 1][hairIndex % 8]);
        leftHair.flip(true, false);
        hairFrames.put(DirectionType.Left, leftHair);

        // Shirt
        TextureRegion[][] shirtSheet = TextureRegion.split(new Texture("player/clothes/shirts.png"), 8, 8);
        shirtFrames.put(DirectionType.Down, shirtSheet[(shirtIndex / 18) * 4][shirtIndex % 16]);
        shirtFrames.put(DirectionType.Right, shirtSheet[(shirtIndex / 18) * 4 + 1][shirtIndex % 16]);
        shirtFrames.put(DirectionType.Left, shirtSheet[(shirtIndex / 18) * 4 + 2][shirtIndex % 16]);
        shirtFrames.put(DirectionType.Up, shirtSheet[(shirtIndex / 18) * 4 + 3][shirtIndex % 16]);

        // Body, Hand, Pant
        TextureRegion[][] bodySheet = TextureRegion.split(new Texture("player/body_boy.png"), 16, 32);
        this.bodyAnimations = new PlayerAnimationFrames(bodySheet);
        TextureRegion[][] hand01Sheet = TextureRegion.split(new Texture("player/hand_01.png"), 16, 32);
        this.hand01Animations = new PlayerAnimationFrames(hand01Sheet);
        TextureRegion[][] hand02Sheet = TextureRegion.split(new Texture("player/hand_02.png"), 16, 32);
        this.hand02Animations = new PlayerAnimationFrames(hand02Sheet);
        TextureRegion[][] pantSheet = TextureRegion.split(new Texture("player/pants/pant_" + pantIndex + ".png"), 16, 32);
        this.pantAnimations = new PlayerAnimationFrames(pantSheet);
    }

    public void render() {
        SpriteBatch batch = StardewValley.getBatch();
        stateTime += Gdx.graphics.getDeltaTime();

        StateType state = App.getGame().getCurrentPlayer().getState();
        DirectionType direction = App.getGame().getCurrentPlayer().getDirection();

        Coordinate cord = App.getGame().getCurrentPlayer().getCoordinate();
        float x = cord.getX();
        float y = cord.getY();

        TextureRegion bodyFrame, handFrame, pantFrame, hairFrame, shirtFrame;
        int frameIndex = 0;
        if (!state.equals(StateType.Idle)) {
            Animation<TextureRegion> bodyAnim = bodyAnimations.getAnimation(state, direction);
            bodyAnim.setPlayMode(Animation.PlayMode.LOOP);
            frameIndex = bodyAnim.getKeyFrameIndex(stateTime);

            Animation<TextureRegion> handAnim = hand01Animations.getAnimation(state, direction);
            Animation<TextureRegion> pantAnim = pantAnimations.getAnimation(state, direction);


            bodyFrame = bodyAnim.getKeyFrame(stateTime, true);
            handFrame = handAnim.getKeyFrame(stateTime, true);
            pantFrame = pantAnim.getKeyFrame(stateTime, true);
        } else {
            bodyFrame = bodyAnimations.getIdleFrame(direction);
            handFrame = hand01Animations.getIdleFrame(direction);
            pantFrame = pantAnimations.getIdleFrame(direction);
        }

        hairFrame = hairFrames.get(direction);
        shirtFrame = shirtFrames.get(direction);

        Coordinate hairOffset = OffsetManager.getOffset(OffsetType.Hair, state, direction, frameIndex);
        Coordinate shirtOffset = OffsetManager.getOffset(OffsetType.Shirt, state, direction, frameIndex);

        float scale = 6f;
        batch.draw(bodyFrame, x, y, bodyFrame.getRegionWidth() * scale, bodyFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getPantColor()));
        batch.draw(pantFrame, x, y, pantFrame.getRegionWidth() * scale, pantFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);

        batch.draw(shirtFrame, x + shirtOffset.getX() * scale, y + shirtOffset.getY() * scale, shirtFrame.getRegionWidth() * scale, shirtFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getHairColor()));
        batch.draw(hairFrame, x + hairOffset.getX() * scale, y + hairOffset.getY() * scale, hairFrame.getRegionWidth() * scale, hairFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);

        batch.draw(handFrame, x, y, handFrame.getRegionWidth() * scale, handFrame.getRegionHeight() * scale);
    }
}
