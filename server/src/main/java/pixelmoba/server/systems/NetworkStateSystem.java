package pixelmoba.server.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IntervalIteratingSystem;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.components.PlayerComponent;
import pixelmoba.server.components.TransformComponent;
import pixelmoba.server.components.singleton.PlayerPositionsSingleton;
import pixelmoba.shared.Network;

public class NetworkStateSystem extends IntervalIteratingSystem {

    private PlayerPositionsSingleton playerPos;

    private Server server;

    protected ComponentMapper<PlayerComponent> playerComponentMap;
    protected ComponentMapper<TransformComponent> positionComponentMap;

    public NetworkStateSystem(Server server) {
        super(Aspect.all(PlayerComponent.class), Network.TICK_RATE);
        this.server = server;
    }

    @Override
    protected void begin() {
        playerPos.dto.players.clear();
    }

    @Override
    protected void process(int entityId) {
        PlayerComponent pComp = playerComponentMap.get(entityId);
        TransformComponent posComp = positionComponentMap.get(entityId);
        playerPos.dto.players.put(pComp.id, posComp.pos.cpy());
    }

    @Override
    protected void end() {
        server.sendToAllUDP(playerPos.dto);
    }
}
