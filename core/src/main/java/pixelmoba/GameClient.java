package pixelmoba;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

public class GameClient extends ApplicationAdapter {

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}