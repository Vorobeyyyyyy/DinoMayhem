package com.sosoft.skyfighter.levels;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.TilemapCollisionParser;

import javax.xml.transform.Templates;

import java.util.ArrayList;

import static com.sosoft.skyfighter.levels.Constants.PPM;

public class Level1 implements Screen {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    LevelCamera camera;
    World world;
    ArrayList<Hero> players = new ArrayList<Hero>();
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    RayHandler rayHandler;
    Light light;



    @Override
    public void show() {
        tiledMap = new TmxMapLoader().load("Tilemaps/Map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        world = new World(new Vector2(0, -10), false);
        players.add(new Hero(world, 600, 600, null));
        players.add(new Hero(world, 600, 600, Controllers.getControllers().first()));
        camera = new LevelCamera(players);
        TilemapCollisionParser.parseCollisionLayer(world, tiledMap.getLayers().get("collision-layer").getObjects());

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 0.7f);
        light = new PointLight(rayHandler, 1000, Color.BLUE, 1200, 500, 500);
        for (Hero player : players)
            light.attachToBody(player.body);

    }

    @Override
    public void render(float delta) {
        //UPDATE PART
        world.step(1 / 60f, 6, 2);
        for (Hero player : players)
            player.update();
        camera.position.set(players.get(0).pos, 0);
        camera.update();
        renderer.setView(camera);

        //RENDER PART
        Gdx.gl.glClearColor(0.1f, 0.5f, 0.5f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        renderer.getBatch().begin();
        //renderContactPoints();
        for (Hero player : players)
            player.draw(renderer.getBatch());
        renderer.getBatch().end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        rayHandler.setCombinedMatrix(camera);
        //rayHandler.updateAndRender();


    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width);
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

    Texture tex = new Texture("badlogic.jpg");

    private void renderContactPoints() {
        for (Contact contact : world.getContactList())
            for (Vector2 point : contact.getWorldManifold().getPoints())
                renderer.getBatch().draw(tex, point.x * PPM, point.y * PPM, 20, 20);

    }

}
