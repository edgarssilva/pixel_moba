package pixelmoba.listeners;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.factories.EntitiesFactory;
import pixelmoba.shared.dto.PlayerJoinedDto;
import pixelmoba.shared.listeners.AbstractListener;

public class JoinedListener extends AbstractListener<PlayerJoinedDto> {

    private final PooledEngine engine;

    public JoinedListener(PooledEngine engine) {
        super(PlayerJoinedDto.class);
        this.engine = engine;
    }

    @Override
    public void trigger(Connection connection, PlayerJoinedDto object) {
        Gdx.app.postRunnable(() -> EntitiesFactory.createNetworkPlayer(engine, object.id, object.pos, false));
    }
}


