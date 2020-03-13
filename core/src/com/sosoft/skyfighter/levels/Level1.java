package com.sosoft.skyfighter.levels;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sosoft.skyfighter.GameInput;
import com.sosoft.skyfighter.Player;
import com.sosoft.skyfighter.TilemapCollisionParser;

public class Level1 implements Screen {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    World world;
    Player player;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    GameInput input;

    RayHandler rayHandler;
    Light light;


    @Override
    public void show() {
        tiledMap = new TmxMapLoader().load("Tilemaps/Map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera(2560, 1440);
        world = new World(new Vector2(0, -10), false);
        player = new Player(world, 600, 600);
        TilemapCollisionParser.parseCollisionLayer(world,tiledMap.getLayers().get("collision-layer").getObjects());

        input = new GameInput();
        Gdx.input.setInputProcessor(input);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.3f,0.3f,0.3f,0.7f);
        light = new PointLight(rayHandler, 1000, Color.BLUE, 1200, 500, 500);
        light.attachToBody(player.getBody());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(600, 600, 0);
        camera.position.set(player.pos, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        player.update(world);
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

        box2DDebugRenderer.render(world, camera.combined);

        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();

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
