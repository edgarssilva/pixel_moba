package pixelmoba.shared.network;

public final class NetworkJob {

    final int connectionId;
    final Object object;

    public NetworkJob(int connectionId, Object object) {
        this.connectionId = connectionId;
        this.object = object;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public Object getObject() {
        return object;
    }
}