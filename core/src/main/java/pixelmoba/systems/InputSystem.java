package pixelmoba.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.mostlyoriginal.api.system.core.PassiveSystem;
import pixelmoba.components.singletons.InputSingleton;
import pixelmoba.shared.Constants;

public class InputSystem extends PassiveSystem implements InputProcessor {

    private final OrthographicCamera camera;
    private InputSingleton input;

    public InputSystem(OrthographicCamera camera) {
        Gdx.input.setInputProcessor(this);
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        input.addAction(Constants.ACTIONS.keyBinding.get(keycode));
        /*inputState.pressedKeys.add(keycode);*/
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*inputState.pressedKeys.remove(Integer.valueOf(keycode));*/
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      /*  Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 1));
        inputState.mousePos.set(worldPos.x, worldPos.y);
        inputState.pressedButtons.add(button);*/

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
}
