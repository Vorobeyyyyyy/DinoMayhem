package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.controllers.ControllerManagerStub;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.sosoft.skyfighter.levels.Constants.PPM;
import static com.sosoft.skyfighter.levels.Constants.RESPAWN_TIME;

public class Hero {
    HeroInputProcessor inputProcessor;
    Sprite sprite;
    World world;

    public int maxSpeed = 20;
    public Vector2 pos = new Vector2();
    public Body body;
    public HeroState state = new HeroState();

    public Hero(World world, float posX, float posY, Controller controller) {
        this.world = world;
        sprite = new Sprite(new Texture("Players/MANpapich.png"));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(posX / PPM, posY / PPM);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / PPM, sprite.getHeight() / 2 / PPM);
        body.setGravityScale(5);
        body.setFixedRotation(true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        shape.dispose();


        if (controller != null)
            controller.addListener(new HeroControllerProcessor(this));
        else {
            inputProcessor = new HeroInputProcessor(this);
            Gdx.input.setInputProcessor(inputProcessor);
        }
    }


    public void update(float delta) {
        pos.x = (body.getPosition().x - sprite.getWidth() / 2 / PPM) * PPM;
        pos.y = (body.getPosition().y - sprite.getHeight() / 2 / PPM) * PPM;
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        updateState(delta);
        isGrounded();
        updateMov();
    }


    public void updateMov() {
        //body.setLinearVelocity(0, body.getLinearVelocity().y);
        if (state.jump && state.grounded) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyForceToCenter(0, 2000 * body.getMass(), true);
        }
        if (state.left && body.getLinearVelocity().x > -maxSpeed) {
            body.setLinearVelocity(-maxSpeed, body.getLinearVelocity().y);
        }
        if (state.right && body.getLinearVelocity().x < maxSpeed) {
            body.setLinearVelocity(maxSpeed, body.getLinearVelocity().y);
        }
        if ((!state.left && !state.right) || (state.left && state.right))
            body.setLinearVelocity(0, body.getLinearVelocity().y);
    }

    private void isGrounded() {
        state.grounded = false;
        for (Contact contact : world.getContactList())
            if (contact.getFixtureA() == body.getFixtureList().first() || contact.getFixtureB() == body.getFixtureList().first()) {
                int counter = 0;
                for (Vector2 point : contact.getWorldManifold().getPoints()) {
                    if (point.y * PPM < pos.y && (point.x * PPM > pos.x - 1 && point.x * PPM < pos.x + sprite.getWidth() + 1) && (point.y * PPM > pos.y - 1 & point.y * PPM < pos.y + sprite.getHeight() + 1)) {
                        state.grounded = true;
                        return;
                    }
                }
            }
    }

    private void updateState(float delta) {
        if (state.dead)
            state.respawnTime -= delta;
        else
            state.respawnTime = RESPAWN_TIME;
        if (pos.y < -200)
            state.dead = true;
    }

    public void reset()
    {
        state.dead = false;
        state.health = state.maxHealth;
    }
    public void draw(Batch batch) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

}
