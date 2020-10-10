package pixelmoba.listeners;

import com.artemis.*;
import com.artemis.utils.IntBag;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.components.NetworkComponent;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.listeners.AbstractListener;

public class DisconnectListener extends AbstractListener<PlayerDisconnectDto> {

    private final World world;
    private final EntitySubscription subscription;

    protected BaseComponentMapper<NetworkComponent> networkCompMap;

    public DisconnectListener(World world) {
        super(PlayerDisconnectDto.class);
        this.world = world;
        subscription = world.getAspectSubscriptionManager().get(Aspect.all(NetworkComponent.class));
        networkCompMap = ComponentMapper.getFor(NetworkComponent.class, world);
    }

    @Override
    public void trigger(Connection connection, PlayerDisconnectDto object) {
        IntBag entities = subscription.getEntities();
        int[] ids = entities.getData();
        for (int i = 0, s = entities.size(); s > i; i++) {
            int entity = ids[i];
            NetworkComponent netComp = networkCompMap.get(entity);

            if (netComp.id == object.id) {
                world.delete(entity);
                return;
            }
        }
    }
}
