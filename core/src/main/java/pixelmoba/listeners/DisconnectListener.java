package pixelmoba.listeners;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.components.NetworkComponent;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.listeners.AbstractListener;

import java.util.Iterator;

public class DisconnectListener extends AbstractListener<PlayerDisconnectDto> {

    private final PooledEngine engine;

    protected ComponentMapper<NetworkComponent> networkCompMap = ComponentMapper.getFor(NetworkComponent.class);

    public DisconnectListener(PooledEngine engine) {
        super(PlayerDisconnectDto.class);
        this.engine = engine;
    }

    @Override
    public void trigger(Connection connection, PlayerDisconnectDto object) {
        Iterator<Entity> iterator = engine.getEntitiesFor(Family.all(NetworkComponent.class).get()).iterator();

        Entity entity;
        while (iterator.hasNext()) {
            entity = iterator.next();
            NetworkComponent netComp = networkCompMap.get(entity);

            if (netComp.id == object.id) {
                engine.removeEntity(entity);
                return;
            }
        }
    }
}
