package pixelmoba.listeners;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.factories.EntitiesFactory;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.listeners.AbstractListener;

import java.util.Map;

public class ConnectionListener extends AbstractListener<PlayerConnectionDto> {

    private final PooledEngine engine;

    public ConnectionListener(PooledEngine engine) {
        super(PlayerConnectionDto.class);
        this.engine = engine;
    }

    @Override
    public void trigger(Connection connection, PlayerConnectionDto object) {
        Gdx.app.postRunnable(() -> {
            System.out.println(object.pos.toString());
            EntitiesFactory.createNetworkPlayer(engine, object.id, object.pos, true);
            for (Map.Entry<Long, Vector2> entry : object.players.entrySet()) {
                long id = entry.getKey();
                Vector2 pos = entry.getValue();

                EntitiesFactory.createNetworkPlayer(engine, id, pos, false);
            }
        });
    }
}
