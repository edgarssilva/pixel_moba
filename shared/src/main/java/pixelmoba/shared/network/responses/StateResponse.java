package pixelmoba.shared.network.responses;

import com.badlogic.gdx.math.Vector2;
import pixelmoba.shared.network.Response;

import java.util.HashMap;

public class StateResponse extends Response {
    public HashMap<Integer, Vector2> playersPos;
}
