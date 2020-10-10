package pixelmoba.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends PooledComponent {
    public Vector2 pos = new Vector2();

    @Override
    public void reset() {
        pos.setZero();
    }
}
