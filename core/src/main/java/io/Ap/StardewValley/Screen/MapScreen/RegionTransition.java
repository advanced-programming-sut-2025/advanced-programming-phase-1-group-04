package io.Ap.StardewValley.Screen.MapScreen;
import io.Ap.StardewValley.Model.Map.Coordinate;

public enum RegionTransition {
    // Row 0
    FROM_00_TO_01(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_01_TO_00(new Coordinate(0, 1), new Coordinate(0, 0), new Coordinate(5, 39), 5 * 16f, 10 * 16f),

    FROM_01_TO_02(new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_02_TO_01(new Coordinate(0, 2), new Coordinate(0, 1), new Coordinate(5, 39), 5 * 16f, 10 * 16f),

    FROM_00_TO_10(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_10_TO_00(new Coordinate(1, 0), new Coordinate(0, 0), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    FROM_01_TO_11(new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_11_TO_01(new Coordinate(1, 1), new Coordinate(0, 1), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    FROM_02_TO_12(new Coordinate(0, 2), new Coordinate(1, 2), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_12_TO_02(new Coordinate(1, 2), new Coordinate(0, 2), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    // Row 1
    FROM_10_TO_11(new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_11_TO_10(new Coordinate(1, 1), new Coordinate(1, 0), new Coordinate(5, 39), 5 * 16f, 10 * 16f),

    FROM_11_TO_12(new Coordinate(1, 1), new Coordinate(1, 2), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_12_TO_11(new Coordinate(1, 2), new Coordinate(1, 1), new Coordinate(5, 39), 5 * 16f, 10 * 16f),

    FROM_10_TO_20(new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_20_TO_10(new Coordinate(2, 0), new Coordinate(1, 0), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    FROM_11_TO_21(new Coordinate(1, 1), new Coordinate(2, 1), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_21_TO_11(new Coordinate(2, 1), new Coordinate(1, 1), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    FROM_12_TO_22(new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(0, 5), 10 * 16f, 5 * 16f),
    FROM_22_TO_12(new Coordinate(2, 2), new Coordinate(1, 2), new Coordinate(29, 5), 10 * 16f, 29 * 16f),

    // Row 2
    FROM_20_TO_21(new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_21_TO_20(new Coordinate(2, 1), new Coordinate(2, 0), new Coordinate(5, 39), 5 * 16f, 10 * 16f),

    FROM_21_TO_22(new Coordinate(2, 1), new Coordinate(2, 2), new Coordinate(5, 0), 5 * 16f, 10 * 16f),
    FROM_22_TO_21(new Coordinate(2, 2), new Coordinate(2, 1), new Coordinate(5, 39), 5 * 16f, 10 * 16f);

    // -----------------------

    private final Coordinate fromRegion;
    private final Coordinate toRegion;
    private final Coordinate destinationCoordinate;
    private final float libGdxX;
    private final float libGdxY;

    RegionTransition(Coordinate fromRegion, Coordinate toRegion, Coordinate destinationCoordinate,
                     float libGdxX, float libGdxY) {
        this.fromRegion = fromRegion;
        this.toRegion = toRegion;
        this.destinationCoordinate = destinationCoordinate;
        this.libGdxX = libGdxX;
        this.libGdxY = libGdxY;
    }

    public boolean matches(Coordinate from, Coordinate to) {
        return from.equals(fromRegion) && to.equals(toRegion);
    }

    public static RegionTransition get(Coordinate from, Coordinate to) {
        for (RegionTransition rt : values()) {
            if (rt.matches(from, to))
                return rt;
        }
        return null;
    }

    public Coordinate getFromRegion() {
        return fromRegion;
    }

    public Coordinate getToRegion() {
        return toRegion;
    }

    public Coordinate getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public float getLibGdxX() {
        return libGdxX;
    }

    public float getLibGdxY() {
        return libGdxY;
    }
}
