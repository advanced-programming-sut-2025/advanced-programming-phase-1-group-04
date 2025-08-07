package io.Ap.StardewValley.Screen.PlayerScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.Model.Tool.*;
import io.Ap.StardewValley.Model.Tool.Tool;
import io.Ap.StardewValley.StardewValley;
import org.w3c.dom.Text;

import java.util.EnumMap;
import java.util.Map;

public class PlayerRender {
    private float stateTime = 0f;

    private BankPlayerAnimationFrames bodyAnimations;
    private BankPlayerAnimationFrames hand01Animations;
    private BankPlayerAnimationFrames hand02Animations;
    private BankPlayerAnimationFrames pantAnimations;

    private BankToolAnimation toolAnimations;

    private final Map<DirectionType, TextureRegion> hairFrames = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, TextureRegion> shirtFrames = new EnumMap<>(DirectionType.class);

    private static Image headImage;

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
        this.bodyAnimations = new BankPlayerAnimationFrames(bodySheet);
        TextureRegion[][] hand01Sheet = TextureRegion.split(new Texture("player/hand_01.png"), 16, 32);
        this.hand01Animations = new BankPlayerAnimationFrames(hand01Sheet);
        TextureRegion[][] hand02Sheet = TextureRegion.split(new Texture("player/hand_02.png"), 16, 32);
        this.hand02Animations = new BankPlayerAnimationFrames(hand02Sheet);
        TextureRegion[][] pantSheet = TextureRegion.split(new Texture("player/pants/pant_" + pantIndex + ".png"), 16, 32);
        this.pantAnimations = new BankPlayerAnimationFrames(pantSheet);

        // make head:
        setHeadImage(bodySheet[0][1], hairSheet[(hairIndex / 8) * 3][hairIndex % 8],
                App.getColor(App.getGame().getCurrentPlayer().getHairColor()), hairIndex);
//
//        //tool
        TextureRegion[][] toolsSheet = TextureRegion.split(new Texture("player/tools/tools.png"), 16, 24);
        this.toolAnimations = new BankToolAnimation(toolsSheet);
    }

    public void setHeadImage(TextureRegion fullHeadFrame, TextureRegion fullHairFrame, Color hairColor, int hairIndex) {
        int longHair = (hairIndex < 16) ? 1 : 0;

        int regionX = fullHeadFrame.getRegionX();
        int regionY = fullHeadFrame.getRegionY();
        int width = 16;
        int height = 16;

        TextureData headData = fullHeadFrame.getTexture().getTextureData();
        if (!headData.isPrepared()) headData.prepare();
        Pixmap headPixmapFull = headData.consumePixmap();

        Pixmap headPixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        headPixmap.drawPixmap(headPixmapFull, 0, 0, regionX, regionY, width, height);

        int hairX = fullHairFrame.getRegionX();
        int hairY = fullHairFrame.getRegionY();

        TextureData hairData = fullHairFrame.getTexture().getTextureData();
        if (!hairData.isPrepared()) hairData.prepare();
        Pixmap hairPixmapFull = hairData.consumePixmap();

        Pixmap hairPixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        hairPixmap.drawPixmap(hairPixmapFull, 0, 0, hairX, hairY, width, height);

        Pixmap combined = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        combined.drawPixmap(headPixmap, 0, 0);

        // color:
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = hairPixmap.getPixel(x, y);

                float r = ((pixel >> 24) & 0xff) / 255f;
                float g = ((pixel >> 16) & 0xff) / 255f;
                float b = ((pixel >> 8) & 0xff) / 255f;
                float a = (pixel & 0xff) / 255f;

                if (a > 0f) {
                    float tintedR = r * hairColor.r;
                    float tintedG = g * hairColor.g;
                    float tintedB = b * hairColor.b;

                    combined.setColor(tintedR, tintedG, tintedB, a);
                    combined.drawPixel(x, y + 1 + longHair);
                }

            }
        }

        Texture finalTexture = new Texture(combined);
        headImage = new Image(finalTexture);
        headImage.setSize(width, height);

        headPixmap.dispose();
        hairPixmap.dispose();
        combined.dispose();
    }


    public static Image getHeadImage() {
        return headImage;
    }

    public void render() {
        SpriteBatch batch = StardewValley.getBatch();
        stateTime += Gdx.graphics.getDeltaTime();

        StateType state = App.getGame().getCurrentPlayer().getState();
        DirectionType direction = App.getGame().getCurrentPlayer().getDirection();

        float x = App.getGame().getCurrentPlayer().getXLibGdx();
        float y = App.getGame().getCurrentPlayer().getYLibGdx();

        TextureRegion bodyFrame, handFrame, pantFrame, hairFrame, shirtFrame;
        TextureRegion toolFrame = null;
        int frameIndex = 0;
        if (!state.equals(StateType.Idle)) {
            Animation<TextureRegion> bodyAnim = bodyAnimations.getAnimation(state, direction);
            bodyAnim.setPlayMode(Animation.PlayMode.LOOP);
            frameIndex = bodyAnim.getKeyFrameIndex(stateTime);

            Animation<TextureRegion> handAnim = hand01Animations.getAnimation(state, direction);
            Animation<TextureRegion> pantAnim = pantAnimations.getAnimation(state, direction);

            Tool tool = App.getGame().getCurrentPlayer().getCurrentTool();
            if (tool != null) {
                Animation<TextureRegion> toolAnim = null;
                if (tool instanceof Hoe hoe) {
                    toolAnim = toolAnimations.getAnimation(tool.getType(), hoe.getLevel(), direction);
                } else if (tool instanceof Axe axe) {
                    toolAnim = toolAnimations.getAnimation(tool.getType(), axe.getLevel(), direction);
                } else if (tool instanceof Pickaxe pickaxe) {
                    toolAnim = toolAnimations.getAnimation(tool.getType(), pickaxe.getLevel(), direction);
                } else if (tool instanceof WateringCan wateringCan) {
                    toolAnim = toolAnimations.getAnimation(tool.getType(), wateringCan.getLevel(), direction);
                }
                if (toolAnim != null) {
                    toolFrame = toolAnim.getKeyFrame(stateTime, true);
                }
            }

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

        ToolRenderTransform toolTransform = ToolOffsetManager.getTransform(state, direction, frameIndex);

        int longHair = (App.getGame().getCurrentPlayer().getHairIndex() < 16) ? 0 : 1;

        float scale = App.getGame().getPlayerScale();

        if (toolFrame != null && direction == DirectionType.Up && (state == StateType.ToolHoe || state == StateType.ToolAxe || state == StateType.ToolPickaxe || state == StateType.ToolWateringCan)) {
            batch.draw(toolFrame,
                    x + toolTransform.getX(), y + toolTransform.getY(),
                    toolTransform.getOriginX(), toolTransform.getOriginY(), // origin for rotation
                    toolFrame.getRegionWidth(), toolFrame.getRegionHeight(),
                    1.1f, 1.1f, // scale
                    toolTransform.getRotation()
            );
        }

        batch.draw(bodyFrame, x, y, bodyFrame.getRegionWidth() * scale, bodyFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getPantColor()));
        batch.draw(pantFrame, x, y, pantFrame.getRegionWidth() * scale, pantFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);

        batch.draw(shirtFrame, x + shirtOffset.getX() * scale, y + shirtOffset.getY() * scale, shirtFrame.getRegionWidth() * scale, shirtFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getHairColor()));
        batch.draw(hairFrame, x + hairOffset.getX() * scale, y + (hairOffset.getY() + longHair) * scale, hairFrame.getRegionWidth() * scale, hairFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);


        if (toolFrame != null && direction != DirectionType.Up && direction != DirectionType.Down && (state == StateType.ToolHoe || state == StateType.ToolAxe || state == StateType.ToolPickaxe || state == StateType.ToolWateringCan)) {
            batch.draw(toolFrame,
                    x + toolTransform.getX(), y + toolTransform.getY(),
                    toolTransform.getOriginX(), toolTransform.getOriginY(), // origin for rotation
                    toolFrame.getRegionWidth(), toolFrame.getRegionHeight(),
                    0.95f, 0.95f, // scale
                    toolTransform.getRotation()
            );
        }

        batch.draw(handFrame, x, y, handFrame.getRegionWidth() * scale, handFrame.getRegionHeight() * scale);

        if (toolFrame != null && direction == DirectionType.Down && (state == StateType.ToolHoe || state == StateType.ToolAxe || state == StateType.ToolPickaxe || state == StateType.ToolWateringCan)) {
            batch.draw(toolFrame,
                    x + toolTransform.getX(), y + toolTransform.getY(),
                    toolTransform.getOriginX(), toolTransform.getOriginY(), // origin for rotation
                    toolFrame.getRegionWidth(), toolFrame.getRegionHeight(),
                    0.95f, 0.95f, // scale
                    toolTransform.getRotation()
            );
        }
    }
}
