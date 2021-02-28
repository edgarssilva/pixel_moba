package pixelmoba.shared.network;

public abstract class Response {
    public final long timestamp;

    public Response() {
        timestamp = System.currentTimeMillis();
    }
}
