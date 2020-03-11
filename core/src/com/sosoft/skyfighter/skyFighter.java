package com.sosoft.skyfighter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class skyFighter extends Game {
    MySpriteBatch batch;
    Player player1;
    World world;


    Body groundBody;
    Body wall1Body;
    Body wall2Body;
    Texture flour;

    @Override
    public void create() {
        batch = new MySpriteBatch();

        world = new World(new Vector2(0, -10), true);
        player1 = createPlayer();


        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(-500, 10));
        groundBody = world.createBody(groundBodyDef);
//        wall1Body = world.createBody(groundBodyDef);
//        wall2Body = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1280f, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
        flour = new Texture("Badlogic.jpg");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(player1);

        player1.update();
        batch.draw(player1);

        batch.draw(flour, groundBody.getPosition().x, groundBody.getPosition().y, 1280, 10);
        doPhysicsStep(1 / 4f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private Player createPlayer() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(600, 600);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(256, 256);


        Body body = world.createBody(def);
        body.setFixedRotation(false);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0f; // Make it bounce a little bit

        Fixture fixture = body.createFixture(fixtureDef);

        Player result = new Player(body);
        shape.dispose();

        return result;
    }

    private float accumulator = 0;

    private void doPhysicsStep(float deltaTime) {
        // fixed time step
        // max frame time to avoid spiral of death (on slow devices)
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1 / 60f) {
            world.step(1 / 60f, 6, 2);
            accumulator -= 1 / 60f;
        }
    }
}
