package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.Animation;
import com.sosoft.skyfighter.levels.LevelController;

import static com.sosoft.skyfighter.levels.Constants.PPM;
import static com.sosoft.skyfighter.levels.Constants.RESPAWN_TIME;
import static java.lang.Math.pow;

public class Hero {
    public HeroInputProcessor inputProcessor = null;
    public LevelController levelController;
    public World world;
    public Vector2 pos = new Vector2();
    public Vector2 centerPos = new Vector2();
    public Body body;
    public HeroState state = new HeroState();
    public int number;
    public Animation animation;
    public Vector2 size;

    public Hero(LevelController levelController, float posX, float posY, Controller controller, int number) {
        size = new Vector2(95, 100);
        world = levelController.world;
        this.levelController = levelController;
        this.number = number;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(posX / PPM, posY / PPM);
        def.gravityScale = 5f;
        def.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2 / PPM, size.y / 2 / PPM);
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

        animation = new Animation();
        animation.addAnimation("wait", new AnimatedSprite("Heroes/dino/wait/wait_", 4, 0.2f, 6));
        animation.addAnimation("walk", new AnimatedSprite("Heroes/dino/walk/walk_", 4, 0.4f, 6));
        animation.setAnimation("walk");
    }

    public void update(float delta) {
        if (inputProcessor != null)
            Gdx.input.setInputProcessor(inputProcessor);
        pos.x = (body.getPosition().x - size.x / 2 / PPM) * PPM;
        pos.y = (body.getPosition().y - size.y / 2 / PPM) * PPM;
        centerPos.x = body.getPosition().x * PPM;
        centerPos.y = body.getPosition().y * PPM;

        updateState(delta);
        updateMov();
        updateAnimation(delta);
    }

    int i = 10;

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

    public void updateAnimation(float delta) {
        boolean lookLeft;
        if (state.aimAngle > 270 || state.aimAngle < 90) {
            animation.flipX = false;
            lookLeft = false;
        }
        else {
            animation.flipX = true;
            lookLeft = true;
        }

        if (state.grounded) {
            if (state.right) {
                animation.setAnimation("walk");
                if (lookLeft)
                    animation.reverse = true;
                else
                    animation.reverse = false;
            } else if (state.left) {
                animation.setAnimation("walk");
                if (lookLeft)
                    animation.reverse = false;
                else
                    animation.reverse = true;
            } else {
                animation.setAnimation("wait");
            }
        } else {
            animation.setAnimation("wait");
        }

        animation.update(delta);
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
        body.setLinearVelocity(0f, 0f);
    }

    public void draw(Batch batch) {
        animation.draw(batch, pos.add(-23, 0));
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

    }
}
