package pixelmoba.shared;

public class Constants {
    public enum DIRECTIONS {
        NORTH, SOUTH, WEST, EAST, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST
    }

    //TODO: Think if I want to "classify" a entity and define other "classes" to collide with
    public enum COLLISION_MASKS {
        CHARACTER //TODO: Name the Characters
    }

    public enum ACTIONS {
        MOVE,
        STOP,

        FOLLOW_CAMERA,

        BASIC_1,
        BASIC_2,
        BASIC_3,
        Ultimate
    }

    public static final int VIRTUAL_WIDTH = 1280; /*640*/
    public static final int VIRTUAL_HEIGHT = 720;   /*360*/


    public static float DISTANCE_THRESHOLD = .5f;
    public static float OVERLAPPING_SPEED = 50f;
}
