package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sosoft.skyfighter.Player;
import com.sosoft.skyfighter.TilemapCollisionParser;

public class Level1 implements Screen {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    World world;
    Player player;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    @Override
    public void show() {
        tiledMap = new TmxMapLoader().load("Tilemaps/Map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera(1920, 1080);
        world = new World(new Vector2(0, -10), false);
        player = new Player(world, 600, 600);
        TilemapCollisionParser.parseCollisionLayer(world,tiledMap.getLayers().get("collision-layer").getObjects());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.position.set(600, 600, 0);
        camera.position.set(player.pos, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        player.update();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

        box2DDebugRenderer.render(world,camera.combined);
        doPhysicsStep(1 / 4f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    float accumulator = 0;

    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 60, 20);
            accumulator -= 1 / 60f;
        }
    }
}
