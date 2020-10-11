package pixelmoba.server.components.singleton;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.dto.PlayersPositionsDto;


@Singleton
public class PlayerPositionsSingleton extends Component {
    public PlayersPositionsDto dto = new PlayersPositionsDto();
}
