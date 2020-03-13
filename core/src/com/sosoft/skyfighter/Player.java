package com.sosoft.skyfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {
    Sprite sprite;
    public Vector2 pos = new Vector2();
    public Body body;

    public Player(World world, int posX, int posY) {
        sprite = new Sprite(new Texture("Players/MANpapich.png"));

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(posX, posY);

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);

        body.setFixedRotation(false);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void update(World world) {
        pos.x = body.getPosition().x - sprite.getWidth() / 2;
        pos.y = body.getPosition().y - sprite.getHeight() / 2;
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        updateMov(world);
    }

    public void updateMov(World world) {
        body.setLinearVelocity(0, body.getLinearVelocity().y);
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            body.applyLinearImpulse(0, 70 * body.getMass(), body.getPosition().x, body.getPosition().y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && body.getLinearVelocity().x > -40) {
            body.applyLinearImpulse(-50 * body.getMass(), 0, body.getPosition().x, body.getPosition().y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && body.getLinearVelocity().x < 40) {
            body.applyLinearImpulse(50 * body.getMass(), 0, body.getPosition().x, body.getPosition().y, true);
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
