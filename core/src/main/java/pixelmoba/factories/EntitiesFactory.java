package pixelmoba.factories;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.components.NetworkComponent;
import pixelmoba.components.TextureComponent;
import pixelmoba.shared.components.PositionComponent;

public class EntitiesFactory {

    public static void createNetworkPlayer(World world, long id, Vector2 pos, boolean owner) {
        int player = world.create();
        EntityEdit playerEdit = world.edit(player);

        NetworkComponent netComp = playerEdit.create((NetworkComponent.class));
        netComp.id = id;
        netComp.owner = owner;

        PositionComponent posComp = playerEdit.create(PositionComponent.class);
        posComp.pos = pos.cpy();

        TextureComponent textureComp = playerEdit.create(TextureComponent.class);
        textureComp.texture = new Texture("dummy.png");

     /*   playerEdit.add(netComp).add(posComp).add(textureComp);*/
    }
}
