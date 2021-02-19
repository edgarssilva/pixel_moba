package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;

@Singleton
public class NetworkStateSingleton extends Component {

    public boolean handled = false;
}
