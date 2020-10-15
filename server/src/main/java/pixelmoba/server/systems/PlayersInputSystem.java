package pixelmoba.server.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import pixelmoba.server.components.PlayerComponent;
import pixelmoba.server.components.TransformComponent;
import pixelmoba.server.components.singleton.PlayersInputSingleton;

@All({PlayerComponent.class, TransformComponent.class})
public class PlayersInputSystem extends IteratingSystem {

    private PlayersInputSingleton playersInput;

    protected ComponentMapper<TransformComponent> transformComponentMap;
    protected ComponentMapper<PlayerComponent> playerComponentMap;

    @Override
    protected void process(int entityId) {
        PlayerComponent pComp = playerComponentMap.get(entityId);
        TransformComponent tComp = transformComponentMap.get(entityId);
        if (playersInput.positions.containsKey(pComp.id))
            tComp.newPos = playersInput.positions.get(pComp.id).cpy();
        else tComp.newPos.set(tComp.pos);

    }

    @Override
    protected void end() {
        playersInput.positions.clear();
    }
}
