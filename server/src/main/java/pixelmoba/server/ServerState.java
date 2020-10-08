package pixelmoba.server;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class ServerState {

    private final HashMap<Long, Vector2> playersPos;

    public ServerState() {
        playersPos = new HashMap<>();
    }


    public Vector2 addPlayer(long id) {
        Vector2 pos = new Vector2(250 * id, 250);
        playersPos.put(id, pos);
        return pos;
    }

    public void removePlayer(long id) {
        playersPos.remove(id);
    }

    public HashMap<Long, Vector2> getPlayersPos() {
        return playersPos;
    }
}
