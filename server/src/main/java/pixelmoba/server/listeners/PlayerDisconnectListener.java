package pixelmoba.server.listeners;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.artemis.utils.IntBag;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.components.PlayerComponent;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.listeners.AbstractListener;

public class PlayerDisconnectListener extends AbstractListener<PlayerDisconnectDto> {
    private final Server server;
    private final World world;

    private EntitySubscription subscription;
    protected ComponentMapper<PlayerComponent> playerComponentMap;

    public PlayerDisconnectListener(Server server, World world) {
        super(PlayerDisconnectDto.class);
        this.server = server;
        this.world = world;
    }

    @Override
    protected void initialize() {
        subscription = world.getAspectSubscriptionManager().get(Aspect.all(PlayerComponent.class));
        playerComponentMap = world.getMapper(PlayerComponent.class);
    }

    @Override
    public void trigger(Connection connection, PlayerDisconnectDto object) {
        connection.close();

        IntBag entities = subscription.getEntities();
        int[] ids = entities.getData();
        for (int i = 0, s = entities.size(); s > i; i++)
            if (object.id == playerComponentMap.get(ids[i]).id) world.delete(ids[i]);

        server.sendToAllExceptTCP(connection.getID(), new PlayerDisconnectDto(object.id));
    }
}
