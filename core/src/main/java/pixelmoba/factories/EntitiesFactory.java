package pixelmoba.factories;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.components.InputComponent;
import pixelmoba.components.TextureComponent;
import pixelmoba.shared.components.NetworkComponent;
import pixelmoba.shared.components.PositionComponent;

public class EntitiesFactory {


    public static int createNetworkPlayer(World world, int id, Vector2 pos, boolean owner) {
        int player = world.create();
        EntityEdit playerEdit = world.edit(player);
        playerEdit.create((NetworkComponent.class)).networkID = id;
        playerEdit.create(PositionComponent.class).pos = pos;
        playerEdit.create(TextureComponent.class).texture = new Texture("./dummy.png");
        if (owner) playerEdit.create(InputComponent.class);
        return player;
    }
}
