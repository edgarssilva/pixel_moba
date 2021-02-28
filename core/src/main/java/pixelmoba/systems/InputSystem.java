package pixelmoba.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pixelmoba.GameClient;
import pixelmoba.components.InputComponent;
import pixelmoba.shared.Constants;
import pixelmoba.shared.components.NetworkComponent;
import pixelmoba.shared.network.requests.InputRequest;

import java.util.LinkedList;

public class InputSystem extends IntervalIteratingSystem implements InputProcessor {

    /*   private InputSingleton input;*/
    private final OrthographicCamera camera;

    private ComponentMapper<NetworkComponent> netCompMap;

    private NetworkSystem networkSystem;

    public LinkedList<Constants.ACTIONS> actions = new LinkedList<>();

    public InputSystem(OrthographicCamera camera) {
        super(Aspect.all(InputComponent.class, NetworkComponent.class), 1f / 30f);
        Gdx.input.setInputProcessor(this);
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (GameClient.clientConfig.getKeysBindings().containsKey(keycode))
            actions.offer(GameClient.clientConfig.getKeysBindings().get(keycode));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        actions.remove(GameClient.clientConfig.getKeysBindings().get(keycode));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (GameClient.clientConfig.getButtonBindings().containsKey(button))
            actions.offer(GameClient.clientConfig.getButtonBindings().get(button));
      /*  Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 1));
        inputState.mousePos.set(worldPos.x, worldPos.y);
        inputState.pressedButtons.add(button);*/

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (GameClient.clientConfig.getButtonBindings().containsKey(button))
            actions.remove(GameClient.clientConfig.getButtonBindings().get(button));
     /*   Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 1));
        inputState.mousePos.set(worldPos.x, worldPos.y);
        inputState.pressedButtons.remove(Integer.valueOf(button));*/
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    protected void process(int entityId) {
        if (actions.isEmpty()) return;
        if (!netCompMap.has(entityId)) return;
        InputRequest request = new InputRequest(netCompMap.get(entityId).networkID);
        request.actions = actions.stream().mapToInt(Constants.ACTIONS::ordinal).toArray();
        networkSystem.getMarshal().sendToAll(request);
    }

}
