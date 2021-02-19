package pixelmoba.shared.network;

import net.mostlyoriginal.api.network.marshal.common.MarshalDictionary;
import pixelmoba.shared.StateRequest;

public class NetworkDictionary extends MarshalDictionary {

    public NetworkDictionary() {
        super(
                StateRequest.class
        );
    }
}
