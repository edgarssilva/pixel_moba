package pixelmoba.server.listeners;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.ServerState;
import pixelmoba.shared.Network;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.dto.PlayerJoinedDto;
import pixelmoba.shared.listeners.AbstractListener;

public class PlayerConnectionListener extends AbstractListener<PlayerConnectionDto> {
    private final Server server;
    private final ServerState serverState;

    public PlayerConnectionListener(Server server, ServerState serverState) {
        super(PlayerConnectionDto.class);
        this.server = server;
        this.serverState = serverState;
    }

    @Override
    public void trigger(Connection connection, PlayerConnectionDto object) {
        long id = Network.COUNTER.getAndIncrement();
        Vector2 newPos = serverState.addPlayer(id);
        PlayerConnectionDto pCon = new PlayerConnectionDto();
        pCon.id = id;
        pCon.pos = newPos.cpy();
        pCon.players = serverState.getPlayersPos();

      /*  for (Map.Entry<EntityID, Vector2> entry : serverState.getPlayersPos().entrySet()) {
            EntityID pEid = entry.getKey();
            Vector2 pos = entry.getValue();

            pCon.players.put(pEid, pos);
        }*/

        PlayerJoinedDto pJoin = new PlayerJoinedDto();
        pJoin.id = id;
        pJoin.pos = newPos.cpy();

        server.sendToTCP(connection.getID(), pCon);
        server.sendToAllExceptTCP(connection.getID(), pJoin);
    }
}
