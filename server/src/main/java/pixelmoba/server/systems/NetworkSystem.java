package pixelmoba.server.systems;

import net.mostlyoriginal.api.network.system.MarshalSystem;
import pixelmoba.server.ServerStrategy;
import pixelmoba.server.components.singletons.NetworkData;
import pixelmoba.shared.network.NetworkDictionary;
import pixelmoba.shared.network.Request;

public class NetworkSystem extends MarshalSystem {

    private NetworkData networkData;

    public NetworkSystem(ServerStrategy strategy) {
        super(new NetworkDictionary(), strategy);
        start();
    }

    @Override
    public void connected(int connectionId) {
        super.connected(connectionId);
    }

    @Override
    public void received(int connectionId, Object object) {
        if (!(object instanceof Request)) return;
        Request request = (Request) object;
        request.sender = connectionId;
        networkData.requests.add(request);
    }

    @Override
    protected void processSystem() {
        super.processSystem();
    }


    @Override
    public void disconnected(int connectionId) {
        super.disconnected(connectionId);
    }

    @Override
    protected void dispose() {
        stop();
    }

}
