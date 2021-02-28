package pixelmoba.server;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import net.mostlyoriginal.api.SingletonPlugin;
import pixelmoba.server.systems.ActionsSystem;
import pixelmoba.server.systems.NetworkSystem;
import pixelmoba.server.systems.RequestProcessor;
import pixelmoba.server.systems.StateSystem;

public class GameServer implements ApplicationListener {
    private World world;

    private final ServerStrategy strategy;
    private final ServerConfiguration config;

    public GameServer(ServerConfiguration config) {
        this.config = config;
        strategy = new ServerStrategy(config);
    }

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder();
        world = new World(worldConfig
                .dependsOn(SingletonPlugin.class)
                .with(new NetworkSystem(strategy))
                .with(new RequestProcessor(strategy, config.getServerFPS()))
                .with(new ActionsSystem())
                .with(new StateSystem(strategy, config.getServerFPS()))
                .build()
        );

        Gdx.app.log("Server initialization", "Server OK");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        world.dispose();
        Gdx.app.exit();
    }
}
