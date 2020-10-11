package pixelmoba.server;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Server;
import net.mostlyoriginal.api.SingletonPlugin;
import pixelmoba.server.listeners.PlayerConnectionListener;
import pixelmoba.server.listeners.PlayerDisconnectListener;
import pixelmoba.server.systems.NetworkStateSystem;
import pixelmoba.shared.listeners.AbstractListener;

public class ServerScreen extends ScreenAdapter {

    private final Server server;
    private World world;

    public ServerScreen(Server server) {
        this.server = server;
    }

    @Override
    public void show() {
        //TODO: Figure out if the listener should be passive systems (need access to singleton components)
        Array<AbstractListener> listeners = new Array<>();

        listeners.add(new PlayerConnectionListener(server, world));
        listeners.add(new PlayerDisconnectListener(server, world));

        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder();

        for (AbstractListener listener : listeners) {
            worldConfig.with(listener);
            server.addListener(listener);
        }

        world = new World(worldConfig
                .dependsOn(SingletonPlugin.class)
                .with(new NetworkStateSystem(server))
                .build()
        );

    }

    @Override
    public void render(float delta) {
        //Update Server logic here
        world.setDelta(delta);
        world.process();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }
}
