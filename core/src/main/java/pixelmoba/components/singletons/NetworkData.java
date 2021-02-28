package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.network.Response;
import pixelmoba.shared.network.responses.StateResponse;

import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Singleton
public class NetworkData extends Component {
    public int playerID;
    public HashMap<Integer, Integer> idMap = new HashMap<>();
    public HashMap<Integer, StateResponse.PlayerState> players = new HashMap<>();
    public Deque<Response> responses = new ConcurrentLinkedDeque<>();
}
