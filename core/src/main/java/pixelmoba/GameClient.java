package pixelmoba;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.mostlyoriginal.api.SingletonPlugin;
import pixelmoba.shared.Constants;
import pixelmoba.systems.InputSystem;
import pixelmoba.systems.NetworkSystem;
import pixelmoba.systems.PositionSystem;
import pixelmoba.systems.RenderSystem;

public class GameClient extends ApplicationAdapter {

    private World world;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private final ClientConfiguration clientConfig;

    public GameClient(ClientConfiguration clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public void create() {
        WorldConfigurationBuilder worldConfig = new WorldConfigurationBuilder();

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        camera.position.set(Constants.VIRTUAL_WIDTH / 2f, Constants.VIRTUAL_HEIGHT / 2f, 0);

        worldConfig
                .dependsOn(SingletonPlugin.class)
                .with(new NetworkSystem(clientConfig))
                .with(new InputSystem(camera)) //Passive System
                .with(new RenderSystem(camera, batch))
                .with(new PositionSystem())
        ;

        world = new World(worldConfig.build());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
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
        batch.dispose();
    }
}