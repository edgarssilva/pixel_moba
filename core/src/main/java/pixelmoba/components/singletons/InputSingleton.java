package pixelmoba.components.singletons;

import com.artemis.Component;
import net.mostlyoriginal.api.Singleton;
import pixelmoba.shared.Constants;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InputSingleton extends Component {
    /*public ArrayList<Integer> pressedKeys = new ArrayList<>();
    public ArrayList<Integer> pressedButtons = new ArrayList<>();
    public Vector2 mousePos = new Vector2();*/

    public AtomicInteger counter = new AtomicInteger();


    public HashMap<Integer, Constants.ACTIONS> actions = new HashMap<>();

    public void addAction(Constants.ACTIONS action) {
        if(action == null) return;

        actions.put(counter.incrementAndGet(), action);
    }
}
