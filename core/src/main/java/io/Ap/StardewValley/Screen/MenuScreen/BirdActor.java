package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BirdActor extends Actor {
    private final Animation<TextureRegion> animation;
    private float time = 0f;

    public BirdActor(Animation<TextureRegion> animation, float x, float y, float scale) {
        this.animation = animation;
        setPosition(x, y);
        setSize(26 * scale, 18 * scale);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;

        float speed = 55;
        float newX = getX() - speed * delta;

        if (newX + getWidth() < 0) {
            newX = Gdx.graphics.getWidth();
        }

        setX(newX);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = animation.getKeyFrame(time, true);
        batch.draw(frame, getX(), getY(), getWidth(), getHeight());
    }
}
