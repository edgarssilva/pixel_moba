package pixelmoba.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.components.NetworkComponent;
import pixelmoba.components.PositionComponent;
import pixelmoba.components.TextureComponent;

public class EntitiesFactory {

    public static void createNetworkPlayer(PooledEngine engine, long id, Vector2 pos, boolean owner) {
        Entity player = engine.createEntity();

        NetworkComponent netComp = engine.createComponent(NetworkComponent.class);
        netComp.id = id;
        netComp.owner = owner;

        PositionComponent posComp = engine.createComponent(PositionComponent.class);
        posComp.pos = pos.cpy();

        TextureComponent textureComp = engine.createComponent(TextureComponent.class);
        textureComp.texture = new Texture("dummy.png");

        player.add(netComp).add(posComp).add(textureComp);
        engine.addEntity(player);
    }
}
