package pixelmoba.server.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.server.ServerStrategy;
import pixelmoba.shared.components.PositionComponent;
import pixelmoba.shared.network.responses.StateResponse;

import java.util.HashMap;

public class StateSystem extends IntervalIteratingSystem {

    private HashMap<Integer, Vector2> playersPos = new HashMap<>();

    private ComponentMapper<PositionComponent> posCompMap;
    private ServerStrategy strategy;

    public StateSystem(ServerStrategy strategy, float interval) {
        super(Aspect.all(PositionComponent.class), interval);
        this.strategy = strategy;
    }

    @Override
    protected void begin() {
        super.begin();
        playersPos.clear();
    }

    @Override
    protected void process(int entityId) {
        playersPos.put(entityId, posCompMap.get(entityId).pos);
    }

    @Override
    protected void end() {
        super.end();
        StateResponse response = new StateResponse();
        response.playersPos = playersPos;
        strategy.sendToAll(response);
    }
}
