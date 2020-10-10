package pixelmoba.listeners;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.factories.EntitiesFactory;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.listeners.AbstractListener;

import java.util.Map;

public class ConnectionListener extends AbstractListener<PlayerConnectionDto> {

    private final World world;

    public ConnectionListener(World world) {
        super(PlayerConnectionDto.class);
        this.world = world;
    }

    @Override
    public void trigger(Connection connection, PlayerConnectionDto object) {
        Gdx.app.postRunnable(() -> {
            System.out.println(object.pos.toString());
            EntitiesFactory.createNetworkPlayer(world, object.id, object.pos, true);
            for (Map.Entry<Long, Vector2> entry : object.players.entrySet()) {
                long id = entry.getKey();
                Vector2 pos = entry.getValue();

                EntitiesFactory.createNetworkPlayer(world, id, pos, false);
            }
        });
    }
}
