package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sosoft.skyfighter.heroes.Bullet;
import com.sosoft.skyfighter.heroes.Hero;

public class LevelContactListener implements ContactListener {
    Level level;

    LevelContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Object objectA = contact.getFixtureA().getBody().getUserData();
        Object objectB = contact.getFixtureB().getBody().getUserData();
        if (objectA instanceof Hero && objectB instanceof Bullet) {

            ((Hero) objectA).state.health -= ((Bullet) objectB).damage;
        }
        if (objectB instanceof Hero && objectA instanceof Bullet)
            ((Hero) objectB).state.health -= ((Bullet)objectA).damage;
        if (objectA instanceof Bullet)
            ((Bullet) objectA).endOfLife = true;
        if (objectB instanceof  Bullet)
            ((Bullet) objectB).endOfLife = true;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
