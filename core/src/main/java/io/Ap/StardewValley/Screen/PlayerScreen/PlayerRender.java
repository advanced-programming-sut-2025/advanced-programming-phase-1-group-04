package io.Ap.StardewValley.Screen.PlayerScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Map.Coordinate;
import io.Ap.StardewValley.StardewValley;

import java.util.EnumMap;
import java.util.Map;

public class PlayerRender {
    private float stateTime = 0f;

    private BankPlayerAnimationFrames bodyAnimations;
    private BankPlayerAnimationFrames hand01Animations;
    private BankPlayerAnimationFrames hand02Animations;
    private BankPlayerAnimationFrames pantAnimations;

    private final Map<DirectionType, TextureRegion> hairFrames = new EnumMap<>(DirectionType.class);
    private final Map<DirectionType, TextureRegion> shirtFrames = new EnumMap<>(DirectionType.class);

    private Image headImage;

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
        TextureRegion hairRegion = new TextureRegion(hairSheet[(hairIndex / 8) * 3][hairIndex % 8], 0, 0, 16, 16);
        TextureRegion headRegion = new TextureRegion(bodySheet[0][1], 0, 0, 16, 16);
        setHeadImage(headRegion, hairRegion);
    }

    private void setHeadImage(TextureRegion head, TextureRegion hair) {
//        int width = 16;
//        int height = 16;
//
//        TextureRegion head = new TextureRegion(new Texture("player/body_boy.png"), 0, 16, 16, 16);
//        TextureRegion[][] hairSheet = TextureRegion.split(new Texture("player/clothes/hairstyles.png"), 16, 32);
//        TextureRegion hair = new TextureRegion(hairSheet[(hairIndex / 8) * 3][hairIndex % 8]);
//        String hairColor = App.getGame().getCurrentPlayer().getHairColor();
//        Color color = App.getColor(hairColor);
//
//        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
//        SpriteBatch batch = new SpriteBatch();
//
//        fbo.begin();
//        Gdx.gl.glClearColor(0, 0, 0, 0);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//
//        // رسم سر
//        batch.setColor(Color.WHITE);
//        batch.draw(head, 0, 0, width, height);
//
//        // رسم مو با رنگ
//        batch.setColor(color);
//        batch.draw(hair, 0, 0, width, height);
//
//        batch.setColor(Color.WHITE);
//        batch.end();
//        fbo.end();
//
//        Texture result = fbo.getColorBufferTexture();
//        TextureRegion region = new TextureRegion(result);
//        region.flip(false, true); // چون FBO تصویر رو برعکس رسم می‌کنه
//
//        headImage = new Image(region);
//        batch.dispose();
//        fbo.dispose(); // اگه فقط یک بار ساختی، آزاد کن
//
//        Pixmap part1 = new Pixmap(Gdx.files.internal("etc/head.png"));
//        //Pixmap part2 = new Pixmap(Gdx.files.internal("pieces/part2.png"));
//
//        //int totalWidth = part1.getWidth() + part2.getWidth(); // جمع عرض‌ها
//        //int maxHeight = Math.max(part1.getHeight(), part2.getHeight()); // بلندترین ارتفاع
//
//        Pixmap finalPixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
//        finalPixmap.drawPixmap(part1, 0, 0); // قرار دادن part1 در (0, 0)
//        //finalPixmap.drawPixmap(part2, part1.getWidth(), 0); // قرار دادن part2 در کنار part1
//        Texture finalTexture = new Texture(finalPixmap);
//
//        headImage = new Image(finalTexture);
    }

    public Image getHeadImage() {
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

        int longHair = (App.getGame().getCurrentPlayer().getHairIndex() < 16) ? 0 : 1;

        float scale = App.getGame().getPlayerScale();
        batch.draw(bodyFrame, x, y, bodyFrame.getRegionWidth() * scale, bodyFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getPantColor()));
        batch.draw(pantFrame, x, y, pantFrame.getRegionWidth() * scale, pantFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);

        batch.draw(shirtFrame, x + shirtOffset.getX() * scale, y + shirtOffset.getY() * scale, shirtFrame.getRegionWidth() * scale, shirtFrame.getRegionHeight() * scale);

        batch.setColor(App.getColor(App.getGame().getCurrentPlayer().getHairColor()));
        batch.draw(hairFrame, x + hairOffset.getX() * scale, y + (hairOffset.getY() + longHair) * scale, hairFrame.getRegionWidth() * scale, hairFrame.getRegionHeight() * scale);
        batch.setColor(Color.WHITE);

        batch.draw(handFrame, x, y, handFrame.getRegionWidth() * scale, handFrame.getRegionHeight() * scale);
    }
}
