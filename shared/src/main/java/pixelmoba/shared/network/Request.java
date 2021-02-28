package pixelmoba.shared.network;

public abstract class Request {
    public final int entityId;
    public final long timestamp;
    public int sender;

    protected Request() {
        this(-1);
    }

    protected  Request(int entityId) {
        this.entityId = entityId;
        timestamp = System.currentTimeMillis();
    }

}
