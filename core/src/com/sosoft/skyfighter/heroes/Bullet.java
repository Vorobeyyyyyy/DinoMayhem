package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.sosoft.skyfighter.levels.Constants.PPM;
import static java.lang.Math.pow;

public class Bullet {
    public final static float INFDISTANCE = -1f;
    public final static float INFTIME = -1f;

    float speed;
    Vector2 pos;
    Vector2 startPos;
    float maxDistance;
    float distance;
    float timeAlive;
    float maxTimeAlive;
    public Body body;
    Texture texture;
    Sprite sprite;
    World world;
    public boolean endOfLife;
    Hero hero;

    public Bullet(World world, float speed, Vector2 pos, Hero hero, float maxDistance, float maxTimeAlive, String spriteName, float scale) {
        this.world = world;
        startPos = pos;
        this.speed = speed;
        this.maxTimeAlive = maxTimeAlive;
        if (maxDistance != INFDISTANCE)
            this.maxDistance = maxDistance * maxDistance;
        else
            this.maxDistance = maxDistance;
        texture = new Texture(spriteName);
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        sprite.setScale(scale);
        timeAlive = 0f;
        this.hero = hero;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(pos.x / PPM, pos.y / PPM);
        bodyDef.bullet = true;
        bodyDef.fixedRotation = false;
        bodyDef.gravityScale = 0;
        bodyDef.angle = hero.state.aimAngle;
        bodyDef.linearVelocity.set(new Vector2(speed, speed).setAngle(hero.state.aimAngle));

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.3f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.restitution = 1f;
        fixtureDef.shape = circleShape;
        fixtureDef.filter.maskBits = (short) (0xFFFF ^ (short) pow(2, hero.number));
        circleShape.dispose();

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        //body.applyForceToCenter(new Vector2(speed, speed).setAngle(direction), true);
    }

    public void update(float delta) {
        pos = body.getPosition().scl(PPM);
        distance = startPos.dst2(pos);
        timeAlive += delta;
        checkIfEnd();
    }

    public void draw(Batch batch) {
        sprite.setPosition(pos.x - sprite.getWidth() / 2, pos.y - sprite.getHeight() / 2);
        sprite.setRotation(body.getLinearVelocity().angle() - 90);
        sprite.draw(batch);
    }

    private void checkIfEnd() {
        if ((distance > maxDistance && maxDistance != INFDISTANCE) ||
                (timeAlive > maxTimeAlive && maxTimeAlive != INFTIME) ||
                pos.x < -400 ||
                pos.y < -400 ||
                pos.x > hero.levelController.level.levelDrawer.camera.mapWidth + 400 ||
                pos.y > hero.levelController.level.levelDrawer.camera.mapHeight + 400) {
            endOfLife = true;
        }
    }

    public void dispose() {
        texture.dispose();
    }
}
