package pixelmoba.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent implements Component, Pool.Poolable {
    public Vector2 pos = new Vector2();

    @Override
    public void reset() {
        pos.setZero();
    }
}
