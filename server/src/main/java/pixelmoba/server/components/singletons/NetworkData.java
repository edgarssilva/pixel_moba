package pixelmoba.server.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.network.Request;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

@Singleton
public class NetworkData extends Component {
    public Deque<Request> requests = new ConcurrentLinkedDeque<>();

}
