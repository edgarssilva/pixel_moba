package pixelmoba.shared.components;

import com.artemis.PooledComponent;

public class NetworkComponent extends PooledComponent {
    public int networkID = -1;

    @Override
    protected void reset() {
        networkID = -1;
    }
}
