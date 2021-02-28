package pixelmoba.shared.network.requests;

import pixelmoba.shared.network.Request;

public class InputRequest extends Request {
    public int[] actions;

    public InputRequest() {
    }

    public InputRequest(int entityId) {
        super(entityId);
    }
}
