package pixelmoba.shared.dto;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class PlayerConnectionDto implements Dto {
    public long id = -1;
    public Vector2 pos;
    public HashMap<Long, Vector2> players;
}
