package pixelmoba.server.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IntervalIteratingSystem;
import pixelmoba.server.ServerStrategy;
import pixelmoba.shared.components.PositionComponent;
import pixelmoba.shared.network.responses.StateResponse;

public class StateSystem extends IntervalIteratingSystem {

    private StateResponse response;

    private ComponentMapper<PositionComponent> posCompMap;
    private final ServerStrategy strategy;

    public StateSystem(ServerStrategy strategy, float interval) {
        super(Aspect.all(PositionComponent.class), interval);
        this.strategy = strategy;
    }

    @Override
    protected void begin() {
        super.begin();
        response = new StateResponse();
    }

    @Override
    protected void process(int entityId) {
        response.players.put(entityId, new StateResponse.PlayerState(
                entityId,
                posCompMap.get(entityId).pos
        ));
    }

    @Override
    protected void end() {
        super.end();
        strategy.sendToAll(response);
    }
}
