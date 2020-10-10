package pixelmoba.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pixelmoba.components.PositionComponent;
import pixelmoba.components.TextureComponent;

@All({PositionComponent.class, TextureComponent.class})
public class RenderSystem extends IteratingSystem {

    protected ComponentMapper<PositionComponent> posCompMap;
    protected ComponentMapper<TextureComponent> textureCompMap;

    private final SpriteBatch batch;

    public RenderSystem() {
        batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void process(int entityId) {
        PositionComponent posComp = posCompMap.get(entityId);
        TextureComponent textureComp = textureCompMap.get(entityId);

        batch.draw(textureComp.texture, posComp.pos.x, posComp.pos.y);
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void dispose() {
        batch.dispose();
    }
}
