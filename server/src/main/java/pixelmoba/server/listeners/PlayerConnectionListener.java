package pixelmoba.server.listeners;

import com.artemis.*;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.components.PlayerComponent;
import pixelmoba.server.components.PositionComponent;
import pixelmoba.server.components.singleton.PlayerPositionsSingleton;
import pixelmoba.shared.Network;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.dto.PlayerJoinedDto;
import pixelmoba.shared.listeners.AbstractListener;

public class PlayerConnectionListener extends AbstractListener<PlayerConnectionDto> {
    private final Server server;
    private final World world;

    private PlayerPositionsSingleton playerPos;

    private EntitySubscription subscription;
    protected ComponentMapper<PlayerComponent> playerComponentMap;

    public PlayerConnectionListener(Server server, World world) {
        super(PlayerConnectionDto.class);
        this.server = server;
        this.world = world;
    }

    @Override
    protected void initialize() {
        subscription = world.getAspectSubscriptionManager().get(Aspect.all(PlayerComponent.class));
    }

    @Override
    public void trigger(Connection connection, PlayerConnectionDto object) {
        int playerEntity = world.create();

        EntityEdit playerEdit = world.edit(playerEntity);

        PlayerComponent pComp = playerEdit.create(PlayerComponent.class);
        pComp.id = Network.COUNTER.getAndIncrement();

        PositionComponent posComp = playerEdit.create(PositionComponent.class);
        posComp.pos = new Vector2(250 * pComp.id, 250);

        //Send data to the clients
        PlayerConnectionDto pCon = new PlayerConnectionDto();
        pCon.id = pComp.id;
        pCon.pos = posComp.pos.cpy();
        pCon.players = playerPos.dto.players;

        PlayerJoinedDto pJoin = new PlayerJoinedDto();
        pJoin.id = pComp.id;
        pJoin.pos = posComp.pos.cpy();

        server.sendToTCP(connection.getID(), pCon);
        server.sendToAllExceptTCP(connection.getID(), pJoin);
    }
}
