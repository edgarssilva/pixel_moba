package pixelmoba.server.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.server.ServerStrategy;
import pixelmoba.server.components.ActionComponent;
import pixelmoba.server.components.singletons.NetworkData;
import pixelmoba.shared.Constants;
import pixelmoba.shared.components.PositionComponent;
import pixelmoba.shared.network.Request;
import pixelmoba.shared.network.requests.ConnectionRequest;
import pixelmoba.shared.network.requests.InputRequest;
import pixelmoba.shared.network.responses.ConnectionResponse;

import java.util.Arrays;

public class RequestProcessor extends IntervalSystem {
    //Singletons
    private NetworkData network;

    private ComponentMapper<ActionComponent> actionMapper;
    private final ServerStrategy strategy;


    public RequestProcessor(ServerStrategy strategy, float interval) {
        super(Aspect.all(/*NetworkComponent.class*/), interval);
        this.strategy = strategy;
    }

    @Override
    protected void processSystem() {
        while (network.requests.peek() != null) {
            Request request = network.requests.pop();
            if (request instanceof ConnectionRequest) processConnectionRequest((ConnectionRequest) request);
            if (request instanceof InputRequest) processInputRequest((InputRequest) request);
        }
    }

    private void processConnectionRequest(ConnectionRequest request) {
        int entity = world.create();
        EntityEdit entityEdit = world.edit(entity);
        entityEdit.create(ActionComponent.class);
        PositionComponent posComp = entityEdit.create(PositionComponent.class);
        posComp.pos = new Vector2(50, 50);

        //Give the server entityId and its position
        ConnectionResponse response = new ConnectionResponse();
        response.networkId = entity;
        response.pos = posComp.pos.cpy();

        strategy.sendTo(request.sender, response);
        System.out.println("Created player: "+entity);
    }

    private void processInputRequest(InputRequest request) {
        if (request.entityId == -1) System.out.println("Invalid EntityId");
        Arrays.stream(request.actions).forEach(a ->
                actionMapper.get(request.entityId).actions
                        .add(Constants.ACTIONS.values()[a]));
    }


}
