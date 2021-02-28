package pixelmoba.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import pixelmoba.components.singletons.NetworkData;
import pixelmoba.shared.components.NetworkComponent;
import pixelmoba.shared.components.PositionComponent;
import pixelmoba.shared.network.responses.StateResponse;

@All({PositionComponent.class})
public class PositionSystem extends IteratingSystem {

    protected ComponentMapper<PositionComponent> positionCompMap;
    private ComponentMapper<NetworkComponent> netCompMap;

    private NetworkData networkData;

    @Override
    protected void process(int entityId) {
        if (!netCompMap.has(entityId)) return;
        if (!networkData.players.containsKey(entityId)) return;
        StateResponse.PlayerState state = networkData.players.get(networkData.idMap.get(entityId));

        PositionComponent posComp = positionCompMap.get(entityId);
        posComp.pos.set(state.pos);
    }

}
