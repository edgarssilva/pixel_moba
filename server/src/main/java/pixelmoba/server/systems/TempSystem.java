package pixelmoba.server.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import pixelmoba.server.components.TransformComponent;

@All(TransformComponent.class)
public class TempSystem extends IteratingSystem {

    protected ComponentMapper<TransformComponent> positionComponentMap;

    @Override
    protected void process(int entityId) {
        TransformComponent posComp = positionComponentMap.get(entityId);
        posComp.pos.x += 10 * world.getDelta();
    }
}
