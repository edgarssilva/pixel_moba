package pixelmoba.server;

import com.badlogic.gdx.ScreenAdapter;
import com.esotericsoftware.kryonet.Server;
import pixelmoba.server.listeners.PlayerConnectionListener;
import pixelmoba.server.listeners.PlayerDisconnectListener;
import pixelmoba.shared.Network;

public class ServerScreen extends ScreenAdapter {

    private final Server server;
    private final ServerState serverState;
    private float tick_timer = 0f;

    public ServerScreen(Server server) {
        this.server = server;
        this.serverState = new ServerState();
    }

    @Override
    public void show() {
        //Add Server Listeners here
        server.addListener(new PlayerConnectionListener(server, serverState));
        server.addListener(new PlayerDisconnectListener(server, serverState));
    }

    @Override
    public void render(float delta) {
        //Update Server logic here

        if (tick_timer >= Network.TICK_RATE) {
            tick_timer = 0;
            //Send game state to clients
        } else tick_timer += delta;

        /*        System.out.println(Gdx.graphics.getFramesPerSecond());*/
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
