package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.dto.PlayersPositionsDto;

@Singleton
public class NetworkStateSingleton extends Component {
    public PlayersPositionsDto playerPos;
    public boolean handled = false;
}
