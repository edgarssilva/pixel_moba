package pixelmoba.shared;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ArrayMap;

public class Constants {
    public enum DIRECTIONS {
        NORTH, SOUTH, WEST, EAST, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST
    }

    //TODO: Think if I want to "classify" a entity and define other "classes" to collide with
    public enum COLLISION_MASKS {
        CHARACTER //TODO: Name the Characters
    }

    public enum ACTIONS {
        MOVE(TYPES.MOVEMENT),
        STOP(TYPES.MOVEMENT),

        FOLLOW_CAMERA(TYPES.HUD),

        BASIC_1(TYPES.SKILLS),
        BASIC_2(TYPES.SKILLS),
        BASIC_3(TYPES.SKILLS),
        Ultimate(TYPES.SKILLS);

        public enum TYPES {MOVEMENT, SKILLS, HUD}

        private final TYPES type;
        public static ArrayMap<Integer, ACTIONS> keyBinding = new ArrayMap<>();

        static {
            keyBinding.put(Input.Buttons.RIGHT, MOVE);
            keyBinding.put(Input.Keys.S, STOP);

            keyBinding.put(Input.Keys.SPACE, FOLLOW_CAMERA);

            keyBinding.put(Input.Keys.Q, BASIC_1);
            keyBinding.put(Input.Keys.W, BASIC_2);
            keyBinding.put(Input.Keys.E, BASIC_3);
            keyBinding.put(Input.Keys.R, Ultimate);
        }

        ACTIONS(TYPES type) {
            this.type = type;
        }

        public boolean IsType(TYPES type) {
            return this.type == type;
        }

        public TYPES getType() {
            return type;
        }

        public int getKeyBinding(){
            return keyBinding.getKey(this, true);
        }
    }

    public static final int VIRTUAL_WIDTH = /*1280*/ 640;
    public static final int VIRTUAL_HEIGHT = /*720*/ 360;


    public static float DISTANCE_THRESHOLD = .5f;
    public static float OVERLAPPING_SPEED = 50f;
}
