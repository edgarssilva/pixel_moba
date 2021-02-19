package pixelmoba.server;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Server;
import net.mostlyoriginal.api.network.marshal.common.MarshalState;
import net.mostlyoriginal.api.network.marshal.kryonet.KryonetMarshalStrategy;

import java.io.IOException;

public class ServerStrategy extends KryonetMarshalStrategy {
    private final ServerConfiguration config;

    public ServerStrategy(ServerConfiguration config) {
        this.config = config;
        endpoint = new Server();
    }

    @Override
    protected void connectEndpoint() {
        try {
            ((Server) endpoint).bind(config.getNetwork().getTcpPort(), config.getNetwork().getUdpPort());
            Gdx.app.log("Server initialization", "Listening connections in ports TCP: " + config.getNetwork().getTcpPort() + " and UDP: " + config.getNetwork().getUdpPort());
            state = MarshalState.STARTED;
        } catch (IOException e) {
            e.printStackTrace();
            Gdx.app.log("Server initialization", "Server port binding has FAILED!");
            state = MarshalState.FAILED_TO_START;
        }
    }

    @Override
    public void sendToAll(Object o) {
        ((Server) endpoint).sendToAllUDP(o); //TODO: Check UDP and TCP here
    }

    public void sendTo(int connectionId, Object o) {
        ((Server) endpoint).sendToUDP(connectionId, o);  //TODO: Check UDP and TCP here
    }
}
