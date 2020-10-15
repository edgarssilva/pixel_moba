package pixelmoba.listeners;

import com.esotericsoftware.kryonet.Connection;
import pixelmoba.components.singletons.NetworkStateSingleton;
import pixelmoba.shared.dto.PlayersPositionsDto;
import pixelmoba.shared.listeners.AbstractListener;

public class NetworkStateListener extends AbstractListener<PlayersPositionsDto> {

    private NetworkStateSingleton netState;

    public NetworkStateListener() {
        super(PlayersPositionsDto.class);
    }

    @Override
    public void trigger(Connection connection, PlayersPositionsDto object) {
        if (netState == null) return;
        netState.playerPos = object;
        netState.handled = false;
    }
}
