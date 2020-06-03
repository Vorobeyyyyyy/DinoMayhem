package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.weapons.Bullet;

public class LevelContactListener implements ContactListener {
    Level level;

    LevelContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Object objectA = contact.getFixtureA().getBody().getUserData();
        Object objectB = contact.getFixtureB().getBody().getUserData();
        if ((objectA instanceof Hero && objectB instanceof Bullet) || (objectB instanceof Hero && objectA instanceof Bullet)) {
            if (objectB instanceof Hero) {
                Object temp = objectA;
                objectA = objectB;
                objectB = temp;
            }
            Hero tempHero = (Hero) objectA;
            Bullet tempBullet = (Bullet) objectB;
            tempHero.state.health -= tempBullet.damage;
            if (tempHero.state.health <= 0) {
                Hero killer = tempBullet.hero;
                killer.state.kills++;
                killer.state.streak++;
                tempHero.state.deaths++;
                level.levelDrawer.levelInterface.levelInterfaceEventText.addMessage(killer.name + " just killed " + tempHero.name + " using " + tempBullet.weapon.name, Color.RED);
            }
            level.levelDrawer.levelInterface.arrayDamageText.add(new LevelInterfaceDamageText(String.valueOf(tempBullet.damage), tempHero.centerPos.add(0, tempHero.size.x / 2)));
        }

        if (objectA instanceof Bullet)
            ((Bullet) objectA).endOfLife = true;
        if (objectB instanceof Bullet)
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

