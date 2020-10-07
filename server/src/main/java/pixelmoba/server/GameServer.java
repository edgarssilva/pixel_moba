package pixelmoba.server;

import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.shared.Network;

public class GameServer extends Game {
    private final Server server;

    public GameServer() {
        server = new Server();
        Network.register(server);

        server.getKryo().setRegistrationRequired(false); //Don't throw up when sending non registered classes
        server.getKryo().setWarnUnregisteredClasses(true); //Instead just give an warning
    }

    @Override
    public void create() {

    }
}
