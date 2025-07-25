package io.Ap.StardewValley.Screen;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerRender {
    private float stateTime = 0f;

    // SpriteSheets
    private final TextureRegion[][] bodySheet;
    private final TextureRegion[][] hairSheet;
    private final TextureRegion[][] shirtSheet;
    private final TextureRegion[][] pantsSheet;

    // فریم‌ها برای هر حالت (مثلاً walk_up = فریم‌های 0 تا 3 از ردیف 0)
    private final Map<PlayerState, int[]> frameRanges = Map.of(
            PlayerState.WALK_DOWN, new int[]{0, 3},
            PlayerState.WALK_UP, new int[]{4, 7},
            PlayerState.WALK_LEFT, new int[]{8, 11},
            PlayerState.WALK_RIGHT, new int[]{12, 15},
            PlayerState.FAINT, new int[]{16, 18}
    );

    // افست‌ها برای هر لایه در هر حالت
    private final Map<PlayerState, Map<String, LayerOffset>> offsets = new HashMap<>();

    public PlayerRender() {
        bodySheet = TextureRegion.split(new Texture("sprites/body/farmer_base.png"), 32, 16);
        hairSheet = TextureRegion.split(new Texture("sprites/hair/hair_001.png"), 8, 8);
        shirtSheet = TextureRegion.split(new Texture("sprites/clothes/shirt_001.png"), 8, 8);
        pantsSheet = TextureRegion.split(new Texture("sprites/pants/pants_001.png"), 8, 8);

        initOffsets(); // مقداردهی اولیه افست‌ها
    }

    private void initOffsets() {
        // هر افست بر حسب نیاز دستی تنظیم می‌شه
        offsets.put(PlayerState.WALK_DOWN, Map.of(
                "hair", new LayerOffset(2, 5),
                "shirt", new LayerOffset(0, 0),
                "pants", new LayerOffset(0, 0)
        ));
        offsets.put(PlayerState.WALK_UP, Map.of(
                "hair", new LayerOffset(3, 7),
                "shirt", new LayerOffset(0, 1),
                "pants", new LayerOffset(0, 0)
        ));
        // ادامه بقیه حالات...
    }

    public void render(SpriteBatch batch, Player player, PlayerState state, float delta) {
        stateTime += delta;

        // موقعیت پیکسلی در مپ
        float x = player.getCoordinate().getX() * 16;
        float y = player.getCoordinate().getY() * 16;

        int[] range = x.get(state);
        int frameCount = range[1] - range[0] + 1;
        int frameIndex = (int)(stateTime / 0.15f) % frameCount;

        // فرض: همه در ردیف 0 هستن، اگر در چند ردیف بودن، باید مشخص بشه
        TextureRegion body = bodySheet[0][range[0] + frameIndex];
        TextureRegion hair = hairSheet[0][range[0] + frameIndex];
        TextureRegion shirt = shirtSheet[0][range[0] + frameIndex];
        TextureRegion pants = pantsSheet[0][range[0] + frameIndex];

        // افست‌های این حالت
        Map<String, LayerOffset> os = offsets.get(state);

        batch.draw(body, x, y);
        batch.draw(pants, x + os.get("pants").x, y + os.get("pants").y);
        batch.draw(shirt, x + os.get("shirt").x, y + os.get("shirt").y);
        batch.draw(hair, x + os.get("hair").x, y + os.get("hair").y);
    }
}
