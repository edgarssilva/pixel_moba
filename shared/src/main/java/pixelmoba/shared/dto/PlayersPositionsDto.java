package pixelmoba.shared.dto;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class PlayersPositionsDto implements Dto{
    public HashMap<Long, Vector2> players = new HashMap<>();
}
