package io.Ap.StardewValley.Server;

public class PlayerPosition {
    public int playerId;
    public float x, y;

    public PlayerPosition() {

    }

    public PlayerPosition(int playerId, float x, float y) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
    }
}

