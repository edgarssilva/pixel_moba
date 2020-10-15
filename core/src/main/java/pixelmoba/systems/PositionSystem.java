package pixelmoba.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.components.NetworkComponent;
import pixelmoba.components.PositionComponent;
import pixelmoba.components.singletons.NetworkStateSingleton;

import java.util.Map;

@All(PositionComponent.class)
public class PositionSystem extends IteratingSystem {

    private NetworkStateSingleton netState;

    protected ComponentMapper<PositionComponent> positionComponentMap;
    protected ComponentMapper<NetworkComponent> networkComponentMap;


    @Override
    protected void process(int entityId) {
        if (netState.handled || !networkComponentMap.has(entityId)) return;

        NetworkComponent netComp = networkComponentMap.get(entityId);

        for (Map.Entry<Long, Vector2> player : netState.playerPos.players.entrySet()) {
            if (netComp.id != player.getKey()) continue;

            PositionComponent posComp = positionComponentMap.get(entityId);

            posComp.pos = player.getValue().cpy();
        }
    }

    @Override
    protected void end() {
        netState.handled = true;
    }
}
