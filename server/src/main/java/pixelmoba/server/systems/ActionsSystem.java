package pixelmoba.server.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import pixelmoba.server.components.ActionComponent;
import pixelmoba.shared.Constants;
import pixelmoba.shared.components.PositionComponent;

@All({ActionComponent.class, PositionComponent.class})
public class ActionsSystem extends IteratingSystem {

    private ComponentMapper<ActionComponent> actionMap;
    private ComponentMapper<PositionComponent> posMap;

    @Override
    protected void process(int entityId) {
        ActionComponent actionComp = actionMap.get(entityId);
        PositionComponent posComp = posMap.get(entityId);

        while (actionComp.actions.peek() != null){
            Constants.ACTIONS action = actionComp.actions.poll();
            switch (action) {
                case BASIC_1:
                    posComp.pos.x += -50 * Gdx.graphics.getDeltaTime();
                    break;
                case BASIC_2:
                    posComp.pos.y += 50 * Gdx.graphics.getDeltaTime();
                    break;
                case BASIC_3:
                    posComp.pos.x += 50 * Gdx.graphics.getDeltaTime();
                    break;
                case Ultimate:
                    posComp.pos.y += -50 * Gdx.graphics.getDeltaTime();
                    break;
            }
        }
    }
}
