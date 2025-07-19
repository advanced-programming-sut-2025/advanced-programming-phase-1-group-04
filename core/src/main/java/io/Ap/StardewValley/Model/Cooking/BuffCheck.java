package io.Ap.StardewValley.Model.Cooking;

import io.Ap.StardewValley.Model.Player.Player;

@FunctionalInterface
public interface BuffCheck {
    void applyBuff (Player player);
}
