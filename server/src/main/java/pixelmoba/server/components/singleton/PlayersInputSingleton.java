package pixelmoba.server.components.singleton;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import net.mostlyoriginal.api.Singleton;

import java.util.HashMap;

@Singleton
public class PlayersInputSingleton extends Component {
    public HashMap<Long, Vector2> positions = new HashMap<>();
}
