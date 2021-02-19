package pixelmoba.server.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.network.IRequest;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

@Singleton
public class NetworksRequests extends Component {
    public Deque<IRequest> queue = new ConcurrentLinkedDeque<>();
}
