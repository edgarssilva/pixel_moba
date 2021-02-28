package pixelmoba.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import pixelmoba.components.InputComponent;
import pixelmoba.components.singletons.NetworkData;
import pixelmoba.shared.components.NetworkComponent;
import pixelmoba.shared.components.PositionComponent;

@All({PositionComponent.class, InputComponent.class})
public class PositionSystem extends IteratingSystem {

    protected ComponentMapper<PositionComponent> positionCompMap;
    protected ComponentMapper<InputComponent> inputCompMap;
    private ComponentMapper<NetworkComponent> netCompMap;

    private NetworkData networkData;

    @Override
    protected void process(int entityId) {
        if (!netCompMap.has(entityId)) return;
        if (!networkData.pos.containsKey(entityId)) return;

        positionCompMap.get(entityId).pos.set(networkData.pos.get(netCompMap.get(entityId).networkID));
    }

}
