package pixelmoba.server.components;

import com.artemis.Component;
import pixelmoba.shared.Constants;

import java.util.ArrayDeque;
import java.util.Queue;

public class ActionComponent extends Component {
    public Queue<Constants.ACTIONS> actions = new ArrayDeque<>();
}
