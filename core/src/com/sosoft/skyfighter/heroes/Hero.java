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

public class Hero {
    HeroInputProcessor inputProcessor;
    Sprite sprite;
    World world;

    public int maxSpeed = 70;
    public Vector2 pos = new Vector2();
    public Body body;
    public HeroState state = new HeroState();

    public Hero(World world, int posX, int posY, Controller controller) {
        this.world = world;
        sprite = new Sprite(new Texture("Players/MANpapich.png"));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(posX, posY);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        body.setFixedRotation(true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f; // Make it bounce a little bit
        body.createFixture(fixtureDef);
        shape.dispose();

        inputProcessor = new HeroInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);

        if (controller != null)
            controller.addListener(new HeroControllerProcessor(this));
    }


    public void update() {
        pos.x = body.getPosition().x - sprite.getWidth() / 2;
        pos.y = body.getPosition().y - sprite.getHeight() / 2;
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        isGrounded();
        updateMov();
    }


    public void updateMov() {
        //body.setLinearVelocity(0, body.getLinearVelocity().y);
        if (state.jump && state.grounded) {
            body.applyForceToCenter(0, 4000 * body.getMass(), true);
        }
        if (state.left && body.getLinearVelocity().x > -maxSpeed) {
            body.setLinearVelocity(-maxSpeed, body.getLinearVelocity().y);
        }
        if (state.right && body.getLinearVelocity().x < maxSpeed) {
            body.setLinearVelocity(maxSpeed, body.getLinearVelocity().y);
        }
        if (!state.left && !state.right)
            body.setLinearVelocity(0, body.getLinearVelocity().y);

    }

    private void isGrounded() {
        boolean grounded = true;
        state.grounded = false;
        System.out.println(world.getContactList().size);
        for (Contact contact : world.getContactList())
            if (contact.getFixtureA() == body.getFixtureList().first() || contact.getFixtureB() == body.getFixtureList().first()) {
                grounded = true;
                for (Vector2 point : contact.getWorldManifold().getPoints()) {
                    if (point.y > body.getPosition().y - (sprite.getHeight() / 2 - 1))
                        grounded = false;

                    System.out.print("COLLISION Y: ");
                    System.out.println(point.y);
                    System.out.print("PLAYER Y:");
                    System.out.println(body.getPosition().y - (sprite.getHeight() / 2 - 1));
                }
                if (grounded) {
                    state.grounded = true;
                    return;
                }
            }
    }

    public Body getBody() {
        return body;
    }


    public void draw(Batch batch) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

}
