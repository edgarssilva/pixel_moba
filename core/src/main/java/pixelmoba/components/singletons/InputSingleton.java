package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.Constants;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InputSingleton extends Component {

    public AtomicInteger counter = new AtomicInteger();

    public HashMap<Integer, Constants.ACTIONS> actions = new HashMap<>();

    public void addAction(Constants.ACTIONS action) {
        if (action == null) return;

        actions.put(counter.incrementAndGet(), action);
    }

    public void removeAction(Constants.ACTIONS action) {
        if (!actions.containsValue(action)) return;
        actions.entrySet().removeIf(e -> e.getValue() == action);
    }
}
