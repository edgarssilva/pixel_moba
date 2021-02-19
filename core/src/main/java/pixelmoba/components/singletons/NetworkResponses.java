package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.network.IResponse;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

@Singleton
public class NetworkResponses extends Component {
    public Deque<IResponse> queue = new ConcurrentLinkedDeque<>();
}
