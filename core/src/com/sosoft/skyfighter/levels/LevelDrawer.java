package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.weapons.Bullet;

import static com.sosoft.skyfighter.levels.Constants.PPM;

public class LevelDrawer {

    boolean debug;
    OrthogonalTiledMapRenderer renderer;
    public LevelCamera camera;
    Box2DDebugRenderer box2DDebugRenderer;
    Level level;
    Texture texture;
    Sprite sprite;
    public LevelInterface levelInterface;
    LevelBackground levelBackground;

    Texture tex = new Texture("badlogic.jpg");

    LevelDrawer(Level level, boolean debug) {
        renderer = new OrthogonalTiledMapRenderer(level.tiledMap);
        camera = new LevelCamera(level.players, level.tiledMap);
        box2DDebugRenderer = new Box2DDebugRenderer();
        texture = new Texture("Heroes/Lazer.png");
        sprite = new Sprite(texture);

        levelBackground = new LevelBackground(this, level.levelDesc.getString("backgroundPath"));
        sprite.setScale(30, 5);
        sprite.setOrigin(0, 0);
        levelInterface = new LevelInterface(level);
        this.level = level;
        this.debug = debug;

    }

    private void update() {
        camera.update();
        renderer.setView(camera);
        levelInterface.update();
        if (!level.isPaused)
            levelBackground.update(1/12f);
    }

    public void updateAndRender() {
        update();
        Gdx.gl.glClearColor(0.1f, 0.5f, 0.5f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.getBatch().begin();
        levelBackground.draw(renderer.getBatch());
        renderer.getBatch().end();
        renderer.render();
        renderer.getBatch().begin();
        if (debug)
            renderContactPoints();
        for (Bullet bullet : level.bullets)
            bullet.draw(renderer.getBatch());
        for (Hero player : level.players) {
            player.draw(renderer.getBatch());
            if (debug) {
                sprite.setPosition(player.centerPos.x, player.centerPos.y);
                sprite.setRotation(player.state.aimAngle);
                sprite.draw(renderer.getBatch());
            }
        }
        levelInterface.draw(renderer.getBatch());
        renderer.getBatch().end();
        if (debug)
            box2DDebugRenderer.render(level.levelController.world, camera.combined.scl(PPM));
    }

    private void renderContactPoints() {
        for (int i = 0; i < level.levelController.world.getContactCount(); i++) {
            Contact contact = level.levelController.world.getContactList().get(i);
            for (int j = 0; j < contact.getWorldManifold().getNumberOfContactPoints(); j++) {
                Vector2 point = contact.getWorldManifold().getPoints()[j];
                renderer.getBatch().draw(tex, point.x * PPM, point.y * PPM, 20, 20);
            }
        }
    }

    public void dispose() {
        texture.dispose();
        renderer.dispose();
        tex.dispose();
        box2DDebugRenderer.dispose();
    }
}
