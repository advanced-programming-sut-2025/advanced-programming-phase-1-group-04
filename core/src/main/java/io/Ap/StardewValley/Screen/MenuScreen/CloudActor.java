package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

class CloudActor extends Image {
    private float speed;

    public CloudActor(Texture texture, float speed, float startX, float startY) {
        super(texture);
        this.speed = speed;
        setPosition(startX, startY);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(-speed * delta, 0);

        // وقتی از صفحه خارج شد، دوباره از راست وارد شود
        if (getX() + getWidth() < 0) {
            setX(Gdx.graphics.getWidth());
        }
    }
}
