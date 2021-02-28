package pixelmoba.shared.network;

import com.badlogic.gdx.math.Vector2;
import net.mostlyoriginal.api.network.marshal.common.MarshalDictionary;
import pixelmoba.shared.network.requests.ConnectionRequest;
import pixelmoba.shared.network.requests.InputRequest;
import pixelmoba.shared.network.responses.ConnectionResponse;
import pixelmoba.shared.network.responses.StateResponse;

import java.util.HashMap;

public class NetworkDictionary extends MarshalDictionary {

    public NetworkDictionary() {
        super(
                ConnectionRequest.class,
                InputRequest.class,

                ConnectionResponse.class,
                StateResponse.class,
                StateResponse.PlayerState.class,

                int[].class,
                Vector2.class,
                Vector2[].class,
                HashMap.class
        );
    }
}
