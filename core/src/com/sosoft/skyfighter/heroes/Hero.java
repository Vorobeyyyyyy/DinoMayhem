package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.Animation;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.Weapon;

import static com.sosoft.skyfighter.levels.Constants.*;
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
    public String name;
    public Weapon weapon;
    public Array<Weapon> weapons;

    public Hero(LevelController levelController, float posX, float posY, Controller controller, int number) {
        name = "Player" + number;
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
        fixtureDef.density = 1f;
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
        weapons = new Array<Weapon>();

        reset();
    }

    public void update(float delta) {
        if (inputProcessor != null)
            Gdx.input.setInputProcessor(inputProcessor);
        updatePos();
        updateState(delta);
        updateMov();
        updateAnimation(delta);
        updateWeapon(delta);
    }

    public void updatePos() {
        pos.x = (body.getPosition().x - size.x / 2 / PPM) * PPM;
        pos.y = (body.getPosition().y - size.y / 2 / PPM) * PPM;
        centerPos.x = body.getPosition().x * PPM;
        centerPos.y = body.getPosition().y * PPM;
    }

    public void updateMov() {
        if ((state.jump && state.grounded) || (state.airJump && state.airJumpsRemain > 0)) {
            if (state.airJump) {
                state.airJumpsRemain--;
                state.airJump = false;
            }
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyForceToCenter(0, 100 * state.jumpHeight * body.getMass(), true);
        }

        if (state.grounded) {
            body.setLinearVelocity(body.getLinearVelocity().x * 0.9f, body.getLinearVelocity().y);
            state.airJumpsRemain = state.maxAirJumps;
        }
        if (!(state.left && state.right)) {
            if (state.left && body.getLinearVelocity().x > -state.maxSpeed)
                body.applyForceToCenter(-state.maxSpeed * body.getMass() * 10, 0, false);

            if (state.right && body.getLinearVelocity().x < state.maxSpeed)
                body.applyForceToCenter(state.maxSpeed * body.getMass() * 10, 0, false);
        }
    }

    public void updateWeapon(float delta) {
        int weaponIndex = weapons.indexOf(weapon, true);

        Weapon weaponToTake = weapon;
        if (state.nextWeapon) {
            if (weaponIndex < weapons.size - 1)
                weaponToTake = weapons.get(weaponIndex + 1);
            else
                weaponToTake = weapons.get(0);
            state.nextWeapon = false;
        }
        if (state.prevWeapon) {
            if (weaponIndex != 0)
                weaponToTake = weapons.get(weaponIndex - 1);
            else
                weaponToTake = weapons.get(weapons.size - 1);
            state.prevWeapon = false;
        }
        if (weaponToTake != weapon) {
            weapon.hide();
            weapon = weaponToTake;
            weapon.take();
        }

        weapon.direction = state.aimAngle;
        weapon.animation.direction = state.aimAngle;
        weapon.animation.pos.set(centerPos);
        weapon.animation.flipY = state.lookLeft;

        if (state.fire && !state.dead)
            weapon.fire();
        if (state.reload && !state.dead) {
            weapon.reload();
            state.reload = false;
        }
        weapon.update(delta);
    }

    public void updateAnimation(float delta) {
        animation.flipX = state.lookLeft;

        if (state.grounded) {
            if (state.right) {
                animation.setAnimation("walk");
                animation.reverse = state.lookLeft;
            } else if (state.left) {
                animation.setAnimation("walk");
                animation.reverse = !state.lookLeft;
            } else {
                animation.setAnimation("wait");
            }
        } else {
            animation.setAnimation("wait");
        }
        animation.pos = new Vector2(pos.x - 23, pos.y);
        animation.update(delta);
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
        if (pos.y < -200 || state.health <= 0)
            state.dead = true;
        if (state.dead)
            state.respawnTime -= delta;
        else {
            state.respawnTime = RESPAWN_TIME;
            updateAimAngle();
            isGrounded();
        }

        state.lookLeft = !(state.aimAngle > 270) && !(state.aimAngle < 90);

    }

    public void updateAimAngle() {
        if (inputProcessor != null) {
            int screenX = Gdx.input.getX();
            int screenY = Gdx.input.getY();
            Vector3 pos = new Vector3(screenX, screenY, 0);
            levelController.level.levelDrawer.camera.unproject(pos);
            state.aimAngle = new Vector2(pos.x - centerPos.x, pos.y - centerPos.y).angle();
        } else {
            if (inputProcessor == null && AIMASSIST_ON) {
                Array<Hero> players = levelController.level.players;
                Array<Float> angles = new Array<Float>();
                for (int i = 0; i < players.size; i++)
                    if (players.get(i) != this)
                        angles.add(new Vector2(players.get(i).centerPos.x - centerPos.x, players.get(i).centerPos.y - centerPos.y).angle());
                for (Float angle : angles)
                    if (Math.abs(state.aimAngle - angle) <= AIMASSIST_ANGLE || 360 - state.aimAngle + angle <= AIMASSIST_ANGLE || 360 - angle + state.aimAngle <= AIMASSIST_ANGLE)
                        state.aimAngle = angle;
            }
        }

    }

    public void reset() {
        state.dead = false;
        state.health = state.maxHealth;
        body.setLinearVelocity(0f, 0f);
        updatePos();
    }

    public void draw(Batch batch) {
        animation.draw(batch);
        weapon.draw(batch);
    }

    public void dispose() {
        for (Weapon weapon : weapons)
            weapon.dispose();
    }
}
