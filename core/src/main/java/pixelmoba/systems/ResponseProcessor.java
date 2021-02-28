package pixelmoba.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import pixelmoba.components.singletons.NetworkData;
import pixelmoba.factories.EntitiesFactory;
import pixelmoba.shared.network.responses.ConnectionResponse;
import pixelmoba.shared.network.responses.StateResponse;

public class ResponseProcessor extends IntervalSystem {
    //Singletons
    private NetworkData networkData;

    //Systems
    public ResponseProcessor(float interval) {
        super(Aspect.all(), interval);
    }

    @Override
    protected void processSystem() {
        while (networkData.responses.peek() != null) {
            Object obj = networkData.responses.poll();
            if (obj instanceof ConnectionResponse) processConnectionResponse((ConnectionResponse) obj);
            if (obj instanceof StateResponse) processStateResponse((StateResponse) obj);
        }
    }

    private void processConnectionResponse(ConnectionResponse response) {
        networkData.playerID =
                EntitiesFactory.createNetworkPlayer(world, response.networkId, response.pos, true);
        networkData.idMap.put(response.networkId, networkData.playerID);
    }

    private void processStateResponse(StateResponse response) {
        networkData.players.putAll(response.players);
        for (int id : response.players.keySet())
            if (!networkData.idMap.containsKey(id)) {
                StateResponse.PlayerState playerState = response.players.get(id);
                networkData.idMap.put(id, EntitiesFactory.createNetworkPlayer(
                        world,
                        id,
                        playerState.pos.cpy(),
                        false
                ));
            }


        /*networkData.players.remove(networkData.playerID);*/
    }
}
