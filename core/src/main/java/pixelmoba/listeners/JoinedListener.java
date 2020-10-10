package pixelmoba.listeners;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import pixelmoba.factories.EntitiesFactory;
import pixelmoba.shared.dto.PlayerJoinedDto;
import pixelmoba.shared.listeners.AbstractListener;

public class JoinedListener extends AbstractListener<PlayerJoinedDto> {

    private final World world;

    public JoinedListener(World world) {
        super(PlayerJoinedDto.class);
        this.world = world;
    }

    @Override
    public void trigger(Connection connection, PlayerJoinedDto object) {
        Gdx.app.postRunnable(() -> EntitiesFactory.createNetworkPlayer(world, object.id, object.pos, false));
    }
}


