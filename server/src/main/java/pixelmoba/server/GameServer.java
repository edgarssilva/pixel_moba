package pixelmoba.server;

import com.badlogic.gdx.Game;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.shared.Network;

import java.io.IOException;

public class GameServer extends Game {
    private final Server server;
    private final ServerScreen serverScreen;

    public GameServer() {
        server = new Server();
        Network.register(server);

        server.getKryo().setRegistrationRequired(false); //Don't throw up when sending non registered classes
        server.getKryo().setWarnUnregisteredClasses(true); //Instead just give an warning

        serverScreen = new ServerScreen(server);
    }

    @Override
    public void create() {
        try {
            server.bind(Network.TCP_PORT, Network.UDP_PORT);
            server.start();
            setScreen(serverScreen);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }
    }
}
