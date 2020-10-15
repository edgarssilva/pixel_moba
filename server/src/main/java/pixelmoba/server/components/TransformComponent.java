package pixelmoba.server.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.shared.Constants;

public class TransformComponent extends PooledComponent {

    public Vector2 pos = new Vector2();
    public Vector2 newPos = new Vector2();

    public Constants.DIRECTIONS direction = Constants.DIRECTIONS.SOUTH;

    @Override
    protected void reset() {
        pos.setZero();
        newPos.setZero();
        direction = Constants.DIRECTIONS.SOUTH;
    }
}
