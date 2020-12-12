package Map;

import java.util.Map;
import Math.*;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;


    private static Map<MapDirection, String> CONVERT_DIR_STR = Map.of(
            MapDirection.NORTH, "N",
            MapDirection.SOUTH, "P",
            MapDirection.WEST, "W",
            MapDirection.EAST, "E"
    );
    private static Map<MapDirection, Vector2d> CONVERT_DIR_VEC = Map.of(
            MapDirection.NORTH, new Vector2d(0,1),
            MapDirection.SOUTH, new Vector2d(0,-1),
            MapDirection.WEST, new Vector2d(-1,0),
            MapDirection.EAST, new Vector2d(1,0),
            MapDirection.NORTHEAST, new Vector2d(1,1),
            MapDirection.NORTHWEST, new Vector2d(-1,1),
            MapDirection.SOUTHEAST, new Vector2d(1,-1),
            MapDirection.SOUTHWEST, new Vector2d(-1,-1)
    );

    public MapDirection next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public MapDirection prev() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }

    public Vector2d toUnitVector() {
        return CONVERT_DIR_VEC.get(this);
    }

    public String toString() {
        return CONVERT_DIR_STR.get(this);
    }
}
