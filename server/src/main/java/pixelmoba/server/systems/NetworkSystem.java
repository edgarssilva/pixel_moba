package pixelmoba.server.systems;

import net.mostlyoriginal.api.network.system.MarshalSystem;
import pixelmoba.server.ServerStrategy;
import pixelmoba.server.components.singletons.NetworksRequests;
import pixelmoba.shared.network.IRequest;
import pixelmoba.shared.network.NetworkDictionary;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class NetworkSystem extends MarshalSystem {

    private NetworksRequests networksRequests;
    private final Deque<NetworkJob> netQueue = new ConcurrentLinkedDeque<>();

    public NetworkSystem(ServerStrategy strategy) {
        super(new NetworkDictionary(), strategy);
        start();
    }

    @Override
    public void received(int connectionId, Object object) {
        netQueue.add(new NetworkJob(connectionId, object));
    }

    @Override
    protected void processSystem() {
        super.processSystem();
        while (netQueue.peek() != null) netQueue.poll().process();
    }

    @Override
    public void disconnected(int connectionId) {
        super.disconnected(connectionId);
    }

    @Override
    protected void dispose() {
        stop();
    }

    final class NetworkJob {

        final int connectionId;
        final Object receivedObject;

        NetworkJob(int connectionId, Object receivedObject) {
            this.connectionId = connectionId;
            this.receivedObject = receivedObject;
        }

        void process() {
            if (receivedObject instanceof IRequest)
                networksRequests.queue.add((IRequest) receivedObject);
        }
    }
}
