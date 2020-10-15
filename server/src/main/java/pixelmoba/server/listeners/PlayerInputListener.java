package pixelmoba.server.listeners;

import com.esotericsoftware.kryonet.Connection;
import pixelmoba.server.components.singleton.PlayersInputSingleton;
import pixelmoba.shared.dto.PlayerInputDto;
import pixelmoba.shared.listeners.AbstractListener;

public class PlayerInputListener extends AbstractListener<PlayerInputDto> {

    private PlayersInputSingleton playersInput;
   /* protected ComponentMapper<PlayerComponent> playerComMap;

    private EntitySubscription subscription;*/

    public PlayerInputListener() {
        super(PlayerInputDto.class);
    }

    @Override
    protected void initialize() {
        /* subscription = world.getAspectSubscriptionManager().get(Aspect.all(PlayerComponent.class, TransformComponent.class));*/
    }

    @Override
    public void trigger(Connection connection, PlayerInputDto object) {
      /*  IntBag ids = subscription.getEntities();
        for (int entity : ids.getData()) {
            PlayerComponent pComp = playerComMap.get(entity);
            if (pComp.id != object.id) continue;

            playersInput.positions.put(pComp.id, object.pos);
        }*/
        playersInput.positions.put(object.id, object.pos);
    }
}
