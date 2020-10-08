package pixelmoba.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class NetworkComponent implements Component, Pool.Poolable {
    public long id = -1;
    public boolean owner = false;

    @Override
    public void reset() {
        owner = false;
        id = -1;
    }
}
