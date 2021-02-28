package pixelmoba.shared.network.responses;

import com.badlogic.gdx.math.Vector2;
import pixelmoba.shared.network.Response;

import java.util.HashMap;

public class StateResponse extends Response {
    public HashMap<Integer, PlayerState> players = new HashMap<>();

    public static final class PlayerState {
        public final int entityID;
        public final Vector2 pos;

        public PlayerState() {
            this(-1, new Vector2());
        }

        public PlayerState(int entityID, Vector2 pos) {
            this.entityID = entityID;
            this.pos = pos;
        }
    }
}
