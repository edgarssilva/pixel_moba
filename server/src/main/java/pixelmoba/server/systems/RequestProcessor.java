package pixelmoba.server.systems;

import com.artemis.BaseSystem;
import pixelmoba.server.components.singletons.NetworksRequests;

public class RequestProcessor extends BaseSystem {

    private NetworksRequests requests;

    @Override
    protected void processSystem() {
        while (requests.queue.peek() != null)
            System.out.println(requests.queue.poll());

    }
}
