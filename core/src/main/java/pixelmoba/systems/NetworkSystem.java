package pixelmoba.systems;

import net.mostlyoriginal.api.network.system.MarshalSystem;
import pixelmoba.ClientConfiguration;
import pixelmoba.components.singletons.NetworkResponses;
import pixelmoba.network.ClientStrategy;
import pixelmoba.shared.network.IResponse;
import pixelmoba.shared.network.NetworkDictionary;

public class NetworkSystem extends MarshalSystem {

    private NetworkResponses responses;

    public NetworkSystem(ClientConfiguration config) {
        super(new NetworkDictionary(), new ClientStrategy(config));
        getMarshal().start(); //TODO: Check where to put this
    }

    @Override
    public void received(int connectionId, Object object) {
        if (object instanceof IResponse)
            responses.queue.add((IResponse) object);
    }
}
