package pixelmoba.server.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.ServerState;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.listeners.AbstractListener;

public class PlayerDisconnectListener extends AbstractListener<PlayerDisconnectDto> {
    private final Server server;
    private final ServerState serverState;

    public PlayerDisconnectListener(Server server, ServerState serverState) {
        super(PlayerDisconnectDto.class);
        this.server = server;
        this.serverState = serverState;
    }

    @Override
    public void trigger(Connection connection, PlayerDisconnectDto object) {
        connection.close();

        serverState.removePlayer(object.id);

        server.sendToAllExceptTCP(connection.getID(), new PlayerDisconnectDto(object.id));
    }
}
