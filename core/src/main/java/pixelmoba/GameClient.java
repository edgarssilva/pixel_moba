package pixelmoba;

import com.artemis.*;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Client;
import net.mostlyoriginal.api.SingletonPlugin;
import pixelmoba.components.NetworkComponent;
import pixelmoba.listeners.ConnectionListener;
import pixelmoba.listeners.DisconnectListener;
import pixelmoba.listeners.JoinedListener;
import pixelmoba.listeners.NetworkStateListener;
import pixelmoba.shared.Network;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.listeners.AbstractListener;
import pixelmoba.systems.PositionSystem;
import pixelmoba.systems.RenderSystem;

import java.io.IOException;

public class GameClient extends ApplicationAdapter {

    private World world;
    private Client client;
    private EntitySubscription subscription;

    protected BaseComponentMapper<NetworkComponent> networkCompMap;

    @Override
    public void create() {


        client = new Client();
        client.getKryo().setRegistrationRequired(false); //Don't throw up when sending non registered classes
        client.getKryo().setWarnUnregisteredClasses(true); //Instead just give an warning

        Network.register(client);

        //Connect
        //TODO: Figure out if the listener should be passive systems (need access to singleton components)
        Array<AbstractListener> listeners = new Array<>();

        listeners.add(new ConnectionListener(world));
        listeners.add(new JoinedListener(world));
        listeners.add(new DisconnectListener(world));
        listeners.add(new NetworkStateListener());

        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder();

        worldConfig
                .dependsOn(SingletonPlugin.class)
                .with(new RenderSystem())
                .with(new PositionSystem())
        ;

        for (AbstractListener listener : listeners) {
            worldConfig.with(listener);
            client.addListener(listener);
        }

        client.start();

        try {
            client.connect(Network.TIMEOUT, Network.HOST, Network.TCP_PORT, Network.UDP_PORT);
            client.sendTCP(new PlayerConnectionDto());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }

        world = new World(worldConfig.build());

        networkCompMap = world.getMapper(NetworkComponent.class);
        subscription = world.getAspectSubscriptionManager().get(Aspect.all(NetworkComponent.class));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
        IntBag entities = subscription.getEntities();
        int[] ids = entities.getData();
        for (int i = 0, s = entities.size(); s > i; i++) {
            int entity = ids[i];
            NetworkComponent netComp = networkCompMap.get(entity);
            if (netComp.owner) client.sendTCP(new PlayerDisconnectDto(netComp.id));
        }
    }
}