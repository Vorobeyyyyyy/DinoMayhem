package com.sosoft.skyfighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.sosoft.skyfighter.levels.Level1;
import com.sosoft.skyfighter.levels.Menu;

import java.util.Stack;

public  class  skyFighter extends Game {
    @Override
    public void create() {
        setScreen(new Menu(this));
    }
}
//public class skyFighter extends Game {
//    MySpriteBatch batch;
//    Player player1;
//    World world;
//    Box2DDebugRenderer debugRenderer;
//    OrthographicCamera cam;
//    Stack<Block> blockStack = new Stack<>();
//
//    @Override
//    public void create() {
//        setScreen(new Level1());
//        batch = new MySpriteBatch();
//
//        world = new World(new Vector2(0, -10), true);
//        player1 = new Player(world);
//        blockStack.push(new Block(world, 400, 20, 500, 20));
//        blockStack.push(new Block(world, 0, 0, 50, 300));
//
//        debugRenderer = new Box2DDebugRenderer();
//        cam = new OrthographicCamera(1280, 720);
//        cam.position.set(1280 / 2f, 720 / 2f, 0);
//
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0, 1, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//
//        cam.update();
//        batch.setProjectionMatrix(cam.combined);
//
//        player1.update();
//
//        for (Block a : blockStack)
//            a.draw(batch);
//        batch.draw(player1);
//
//        //debugRenderer.render(world, new Matrix4(cam.combined));
//
//        doPhysicsStep(1 / 4f);
//        batch.end();
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//    }
//
//
//    private float accumulator = 0;
//
//    private void doPhysicsStep(float deltaTime) {
//        // fixed time step
//        // max frame time to avoid spiral of death (on slow devices)
//        float frameTime = Math.min(deltaTime, 0.25f);
//        accumulator += frameTime;
//        while (accumulator >= 1 / 60f) {
//            world.step(1 / 60f, 6, 2);
//            accumulator -= 1 / 60f;
//        }
//    }
//
//}
