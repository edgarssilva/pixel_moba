package pixelmoba.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pixelmoba.components.TextureComponent;
import pixelmoba.shared.components.PositionComponent;

@All({PositionComponent.class, TextureComponent.class})
public class RenderSystem extends IteratingSystem {

    protected ComponentMapper<PositionComponent> posCompMap;
    protected ComponentMapper<TextureComponent> textureCompMap;

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    public RenderSystem(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void process(int entityId) {
        PositionComponent posComp = posCompMap.get(entityId);
        TextureComponent textureComp = textureCompMap.get(entityId);

        batch.draw(textureComp.texture, posComp.pos.x, posComp.pos.y, textureComp.texture.getWidth() / 2f, textureComp.texture.getHeight() / 2f);
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void dispose() {
        /*  batch.dispose();*/
    }
}
