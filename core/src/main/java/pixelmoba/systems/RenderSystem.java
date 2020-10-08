package pixelmoba.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pixelmoba.components.PositionComponent;
import pixelmoba.components.TextureComponent;

import java.util.ArrayList;

public class RenderSystem extends IteratingSystem {

    protected ComponentMapper<PositionComponent> posCompMap = ComponentMapper.getFor(PositionComponent.class);
    protected ComponentMapper<TextureComponent> textureCompMap = ComponentMapper.getFor(TextureComponent.class);

    private final SpriteBatch batch;

    private final ArrayList<Entity> renderQueue;

    public RenderSystem() {
        super(Family.all(PositionComponent.class, TextureComponent.class).get());
        batch = new SpriteBatch();
        renderQueue = new ArrayList<>();
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : renderQueue) {
            PositionComponent posComp = posCompMap.get(entity);
            TextureComponent textureComp = textureCompMap.get(entity);

            batch.begin();
            batch.draw(textureComp.texture, posComp.pos.x, posComp.pos.y);
            batch.end();
        }

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
