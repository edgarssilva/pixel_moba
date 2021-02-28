package pixelmoba.systems;

import net.mostlyoriginal.api.network.system.MarshalSystem;
import pixelmoba.GameClient;
import pixelmoba.components.singletons.NetworkData;
import pixelmoba.network.ClientStrategy;
import pixelmoba.shared.network.NetworkDictionary;
import pixelmoba.shared.network.Response;
import pixelmoba.shared.network.requests.ConnectionRequest;

public class NetworkSystem extends MarshalSystem {

    private NetworkData responses;

    public NetworkSystem() {
        super(new NetworkDictionary(), new ClientStrategy(GameClient.clientConfig));
    }

    @Override
    protected void initialize() {
        getMarshal().start(); //TODO: Check where to put this
    }

    @Override
    public void connected(int connectionId) {
        super.connected(connectionId);
        getMarshal().sendToAll(new ConnectionRequest()); //Send request to create player entity
    }

    @Override
    public void received(int connectionId, Object object) {
        if (object instanceof Response)
            responses.responses.add((Response) object);

        /*Gdx.app.log("Received Response", object.getClass().getName());*/
    }
}
