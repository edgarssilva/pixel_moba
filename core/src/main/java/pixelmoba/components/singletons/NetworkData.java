package pixelmoba.components.singletons;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.network.Response;

import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Singleton
public class NetworkData extends Component {
    public Deque<Response> responses = new ConcurrentLinkedDeque<>();
    public HashMap<Integer, Vector2> pos;
}
