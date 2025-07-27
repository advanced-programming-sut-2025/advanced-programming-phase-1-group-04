package io.Ap.StardewValley.Screen.PlayerScreen;

import io.Ap.StardewValley.Model.Map.Coordinate;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OffsetManager {
    private static final Map<OffsetType, EnumMap<StateType, EnumMap<DirectionType, List<Coordinate>>>> allOffsets = new EnumMap<>(OffsetType.class);

    static {
        // Hair
        EnumMap<StateType, EnumMap<DirectionType, List<Coordinate>>> hairOffsets = new EnumMap<>(StateType.class);

        // walk
        EnumMap<DirectionType, List<Coordinate>> hairWalk = new EnumMap<>(DirectionType.class);
        hairWalk.put(DirectionType.Right, List.of(
                new Coordinate(0, -2),
                new Coordinate(0, -2),
                new Coordinate(0, -1),
                new Coordinate(0, -2),
                new Coordinate(0, -2),
                new Coordinate(0, -1)
        ));
        hairWalk.put(DirectionType.Left, List.of(
                new Coordinate(0, -2),
                new Coordinate(0, -2),
                new Coordinate(0, -1),
                new Coordinate(0, -2),
                new Coordinate(0, -2),
                new Coordinate(0, -1)
        ));
        hairWalk.put(DirectionType.Up, List.of(
                new Coordinate(0, -2),
                new Coordinate(0, -3),
                new Coordinate(0, -2),
                new Coordinate(0, -3)
        ));
        hairWalk.put(DirectionType.Down, List.of(
                new Coordinate(0, -2),
                new Coordinate(0, -3),
                new Coordinate(0, -2),
                new Coordinate(0, -3)
        ));
        hairOffsets.put(StateType.Walk, hairWalk);

        // idle
        EnumMap<DirectionType, List<Coordinate>> hairIdle = new EnumMap<>(DirectionType.class);
        hairIdle.put(DirectionType.Right, List.of(new Coordinate(0, -1)));
        hairIdle.put(DirectionType.Left, List.of(new Coordinate(0, -1)));
        hairIdle.put(DirectionType.Up, List.of(new Coordinate(0, -1)));
        hairIdle.put(DirectionType.Down, List.of(new Coordinate(0, -1)));
        hairOffsets.put(StateType.Idle, hairIdle);

        allOffsets.put(OffsetType.Hair, hairOffsets);

        // ---------------------------------------------------------------------------------------------
        // Shirt
        EnumMap<StateType, EnumMap<DirectionType, List<Coordinate>>> shirtOffsets = new EnumMap<>(StateType.class);

        // walk
        EnumMap<DirectionType, List<Coordinate>> shirtWalk = new EnumMap<>(DirectionType.class);
        shirtWalk.put(DirectionType.Right, List.of(
                new Coordinate(4, 8),
                new Coordinate(4, 8),
                new Coordinate(4, 9),
                new Coordinate(4, 8),
                new Coordinate(4, 8),
                new Coordinate(4, 9)
        ));
        shirtWalk.put(DirectionType.Left, List.of(
                new Coordinate(4, 8),
                new Coordinate(4, 8),
                new Coordinate(4, 9),
                new Coordinate(4, 8),
                new Coordinate(4, 8),
                new Coordinate(4, 9)
        ));
        shirtWalk.put(DirectionType.Up, List.of(
                new Coordinate(4, 9),
                new Coordinate(4, 9),
                new Coordinate(4, 9),
                new Coordinate(4, 9)
        ));
        shirtWalk.put(DirectionType.Down, List.of(
                new Coordinate(4, 8),
                new Coordinate(4, 7),
                new Coordinate(4, 8),
                new Coordinate(4, 7)
        ));
        shirtOffsets.put(StateType.Walk, shirtWalk);

        // idle
        EnumMap<DirectionType, List<Coordinate>> shirtIdle = new EnumMap<>(DirectionType.class);
        shirtIdle.put(DirectionType.Right, List.of(new Coordinate(4, 9)));
        shirtIdle.put(DirectionType.Left, List.of(new Coordinate(4, 9)));
        shirtIdle.put(DirectionType.Up, List.of(new Coordinate(4, 10)));
        shirtIdle.put(DirectionType.Down, List.of(new Coordinate(4, 9)));
        shirtOffsets.put(StateType.Idle, shirtIdle);

        allOffsets.put(OffsetType.Shirt, shirtOffsets);
    }

    public static Coordinate getOffset(OffsetType type, StateType state, DirectionType direction, int frameIndex) {
        EnumMap<StateType, EnumMap<DirectionType, List<Coordinate>>> stateMap = allOffsets.get(type);
        if (stateMap == null) return new Coordinate(0, 0);

        EnumMap<DirectionType, List<Coordinate>> dirMap = stateMap.get(state);
        if (dirMap == null) return new Coordinate(0, 0);

        List<Coordinate> offsets = dirMap.get(direction);
        if (offsets == null || frameIndex < 0 || frameIndex >= offsets.size()) return new Coordinate(0, 0);

        return offsets.get(frameIndex);
    }
}

enum OffsetType {
    Hair,
    Shirt;
}