package pixelmoba.network;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import net.mostlyoriginal.api.network.marshal.common.MarshalState;
import net.mostlyoriginal.api.network.marshal.kryonet.KryonetMarshalStrategy;
import pixelmoba.ClientConfiguration;

import java.io.IOException;

public class ClientStrategy extends KryonetMarshalStrategy {
    private final ClientConfiguration config;

    public ClientStrategy(ClientConfiguration config) {
        this.config = config;
        endpoint = new Client();
    }

    @Override
    protected void connectEndpoint() {
        try {
            ((Client) endpoint).connect(config.getNetwork().getTimeout(), config.getNetwork().getHost(), config.getNetwork().getTcpPort(), config.getNetwork().getUdpPort());
            Gdx.app.log("Server Connection", "Connected to " + config.getNetwork().getHost() + ":" + config.getNetwork().getUdpPort());
            state = MarshalState.STARTED;
        } catch (IOException e) {
            Gdx.app.log("Server Connection", "Failed to connect!");
            state = MarshalState.FAILED_TO_START;
        }
    }

    @Override
    public void start() {
        state = MarshalState.STARTING;
        registerDictionary();
        endpoint.addListener(listener);
        endpoint.start();
        connectEndpoint();
    }

    @Override
    public void stop() {
        super.stop();
        if(state == MarshalState.STOPPED) Gdx.app.log("Server Connection", "Disconnected!");
    }

    @Override
    public void sendToAll(Object o) {
        ((Client) endpoint).sendUDP(o); //TODO: Check UDP and TCP
    }
}
