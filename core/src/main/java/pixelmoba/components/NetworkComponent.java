package pixelmoba.components;

import com.artemis.PooledComponent;

public class NetworkComponent extends PooledComponent {
    public long id = -1;
    public boolean owner = false;

    @Override
    public void reset() {
        owner = false;
        id = -1;
    }
}
