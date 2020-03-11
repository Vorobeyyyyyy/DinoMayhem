package com.sosoft.skyfighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Player {
    Sprite sprite;
    int speed = 100;
    float x = 100;
    float y = 100;

    Body body;

    public Player(Body body1) {
        body = body1;
        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setOriginCenter();
    }

    public Vector2 getPos() {
        Vector2 result = new Vector2(x, y);
        return result;
    }

    public void update() {
        x = body.getPosition().x;
        y = body.getPosition().y;
        updateMov();
    }

    public void updateMov() {
        body.setLinearVelocity(0,body.getLinearVelocity().y);
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            //body.applyAngularImpulse(1);
            body.applyLinearImpulse(0, 70 * body.getMass(), body.getPosition().x, body.getPosition().y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && body.getLinearVelocity().x < 40) {
            body.applyLinearImpulse(-50* body.getMass(), 0, x, y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && body.getLinearVelocity().x > -40) {
            body.applyLinearImpulse(50* body.getMass(), 0, x, y, true);
        }
    }
}
