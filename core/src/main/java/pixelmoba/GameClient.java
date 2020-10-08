package pixelmoba;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.esotericsoftware.kryonet.Client;
import pixelmoba.components.NetworkComponent;
import pixelmoba.listeners.ConnectionListener;
import pixelmoba.listeners.DisconnectListener;
import pixelmoba.listeners.JoinedListener;
import pixelmoba.shared.Network;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.systems.RenderSystem;

import java.io.IOException;
import java.util.Iterator;

public class GameClient extends ApplicationAdapter {

    private PooledEngine engine;
    private Client client;
    protected ComponentMapper<NetworkComponent> networkCompMap = ComponentMapper.getFor(NetworkComponent.class);

    @Override
    public void create() {
        engine = new PooledEngine();
        engine.addSystem(new RenderSystem());

        client = new Client();
        client.getKryo().setRegistrationRequired(false); //Don't throw up when sending non registered classes
        client.getKryo().setWarnUnregisteredClasses(true); //Instead just give an warning

        Network.register(client);

        //Connect
        //Add Listeners
        client.addListener(new ConnectionListener(engine));
        client.addListener(new JoinedListener(engine));
        client.addListener(new DisconnectListener(engine));
        client.start();

        try {
            client.connect(Network.TIMEOUT, Network.HOST, Network.TCP_PORT, Network.UDP_PORT);
            client.sendTCP(new PlayerConnectionDto());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.exit(1);
        }
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

        engine.update(Gdx.graphics.getDeltaTime());
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
        //Set a disconnect for all the players the client owns (Just 1 tbh)
        Iterator<Entity> iterator = engine.getEntitiesFor(Family.all(NetworkComponent.class).get()).iterator();

        Entity entity;
        while (iterator.hasNext()) {
            entity = iterator.next();
            NetworkComponent netComp = networkCompMap.get(entity);
            if (netComp.owner) client.sendTCP(new PlayerDisconnectDto(netComp.id));

        }
    }
}