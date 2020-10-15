package pixelmoba.server.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import pixelmoba.server.components.TransformComponent;
import pixelmoba.shared.Constants;

public class TransformSystem extends IteratingSystem {

    protected ComponentMapper<TransformComponent> transformComponentMap;

    private final Vector2 temp;

    public TransformSystem() {
        super(Aspect.all(TransformComponent.class));
        temp = new Vector2();
    }

    @Override
    protected void process(int entityId) {
        TransformComponent transComp = transformComponentMap.get(entityId);

        float mv = world.delta * 10;

        //Find out where to move
        Vector2 newPos = transComp.newPos, pos = transComp.pos;
        temp.set(newPos).sub(pos);


        //Check for overshoot
        if (temp.len() <= Constants.DISTANCE_THRESHOLD) return;

        updateDirection(transComp, transComp.newPos);

        //Move
        pos.add(temp.nor().scl(mv));
    }


    public static void updateDirection(TransformComponent transComp, Vector2 lookTo) {
        //Get the angle we are moving towards
        double degrees = MathUtils.atan2(lookTo.y - transComp.pos.y, lookTo.x - transComp.pos.x) * MathUtils.radiansToDegrees;

        //Remove negative degrees from (-180 to 180) to (0 to 360)
        if (degrees < 0) degrees += 360;


        if (degrees <= 30 || degrees >= 330) transComp.direction = Constants.DIRECTIONS.EAST;
        if (degrees > 30 && degrees < 60) transComp.direction = Constants.DIRECTIONS.NORTH_EAST;
        if (degrees >= 60 && degrees <= 120) transComp.direction = Constants.DIRECTIONS.NORTH;
        if (degrees > 120 && degrees < 160) transComp.direction = Constants.DIRECTIONS.NORTH_WEST;
        if (degrees >= 160 && degrees <= 220) transComp.direction = Constants.DIRECTIONS.WEST;
        if (degrees > 220 && degrees < 250) transComp.direction = Constants.DIRECTIONS.SOUTH_WEST;
        if (degrees >= 250 && degrees <= 310) transComp.direction = Constants.DIRECTIONS.SOUTH;
        if (degrees > 310 && degrees < 330) transComp.direction = Constants.DIRECTIONS.SOUTH_EAST;
    }
}
