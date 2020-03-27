package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.Animation;
import com.sosoft.skyfighter.levels.LevelController;

import static com.sosoft.skyfighter.levels.Constants.PPM;
import static com.sosoft.skyfighter.levels.Constants.RESPAWN_TIME;
import static java.lang.Math.pow;

public class Hero {
    public HeroInputProcessor inputProcessor = null;
    public Sprite sprite;
    public LevelController levelController;
    public World world;
    public Texture texture;
    public Vector2 pos = new Vector2();
    public Vector2 centerPos = new Vector2();
    public Body body;
    public HeroState state = new HeroState();
    public int number;
    public HeroAnimation heroAnim;

    public Hero(LevelController levelController, float posX, float posY, Controller controller, int number) {
        world = levelController.world;
        this.levelController = levelController;
        this.number = number;
        texture = new Texture("Heroes/Agent/Idle_0.png");
        sprite = new Sprite(texture);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(posX / PPM, posY / PPM);
        def.gravityScale = 5f;
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2 / PPM, sprite.getHeight() / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.filter.categoryBits = (short) pow(2, number);
        fixtureDef.filter.maskBits = 0xFF;
        body = world.createBody(def);
        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose();

        if (controller != null)
            controller.addListener(new HeroControllerProcessor(this));
        else {
            inputProcessor = new HeroInputProcessor(this);
            Gdx.input.setInputProcessor(inputProcessor);
        }
    }

    public void update(float delta) {
        if (inputProcessor != null)
            Gdx.input.setInputProcessor(inputProcessor);
        pos.x = (body.getPosition().x - sprite.getWidth() / 2 / PPM) * PPM;
        pos.y = (body.getPosition().y - sprite.getHeight() / 2 / PPM) * PPM;
        centerPos.x = body.getPosition().x * PPM;
        centerPos.y = body.getPosition().y * PPM;

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        updateState(delta);
        updateMov();
    }


    public void updateMov() {
        if (state.jump && state.grounded) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyForceToCenter(0, 100 * state.jumpHeight * body.getMass(), true);
        }
        if (state.grounded)
            body.setLinearVelocity(body.getLinearVelocity().x * 0.9f, body.getLinearVelocity().y);
        if (!(state.left && state.right)) {
            if (state.left && body.getLinearVelocity().x > -state.maxSpeed)
                body.applyForceToCenter(-state.maxSpeed * body.getMass() * 10, 0, false);

            if (state.right && body.getLinearVelocity().x < state.maxSpeed)
                body.applyForceToCenter(state.maxSpeed * body.getMass() * 10, 0, false);
        }

    }

    public void updateAnimation() {
        if(state.grounded) {

        }
        if(state.right) {

        }
    }

    public void updateAbilities(float delta) {
        if (state.firstAbilityCurrentCooldown <= 0) {
            if (state.firstAbility) {
                firstAbility();
                state.firstAbilityCurrentCooldown = state.firstAbilityCooldown;
            }
        } else
            state.firstAbilityCurrentCooldown -= delta;

        if (state.secondAbilityCurrentCooldown <= 0) {
            if (state.secondAbility) {
                secondAbility();
                state.secondAbilityCurrentCooldown = state.secondAbilityCooldown;
            }
        } else
            state.secondAbilityCurrentCooldown -= delta;

        if (state.thirdAbilityCurrentCooldown <= 0) {
            if (state.thirdAbility) {
                thirdAbility();
                state.thirdAbilityCurrentCooldown = state.thirdAbilityCooldown;
            }
        } else
            state.thirdAbilityCurrentCooldown -= delta;
    }

    private void isGrounded() {
        for (int i = 0; i < world.getContactCount(); i++) {
            Contact contact = world.getContactList().get(i);
            if (contact.getFixtureA() == body.getFixtureList().first() || contact.getFixtureB() == body.getFixtureList().first()) {
                for (int j = 0; j < contact.getWorldManifold().getNumberOfContactPoints(); ++j)
                    if (contact.getWorldManifold().getPoints()[j].y * PPM < pos.y) {
                        {
                            state.grounded = true;
                            return;
                        }
                    }
            }
        }
        state.grounded = false;
    }

    public void updateState(float delta) {
        if (pos.y < -200)
            state.dead = true;
        if (state.dead)
            state.respawnTime -= delta;
        else {
            state.respawnTime = RESPAWN_TIME;
            if (inputProcessor != null)
                updateAimAngle();
            isGrounded();
            updateAbilities(delta);
        }
    }

    private void updateAimAngle() {
        int screenX = Gdx.input.getX();
        int screenY = Gdx.input.getY();
        Vector3 pos = new Vector3(screenX, screenY, 0);
        levelController.level.levelDrawer.camera.unproject(pos);
        state.aimAngle = new Vector2(pos.x - centerPos.x, pos.y - centerPos.y).angle();
    }

    public void reset() {
        state.dead = false;
        state.health = state.maxHealth;
    }

    public void draw(Batch batch) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    public void firstAbility() {
    }

    ;

    public void secondAbility() {
    }

    ;

    public void thirdAbility() {
    }

    ;

    public void dispose() {
        texture.dispose();
    }
}
