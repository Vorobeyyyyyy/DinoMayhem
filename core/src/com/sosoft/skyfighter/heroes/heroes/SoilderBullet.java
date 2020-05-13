package com.sosoft.skyfighter.heroes.heroes;

import com.sosoft.skyfighter.heroes.Bullet;
import com.sosoft.skyfighter.heroes.Hero;

public class SoilderBullet extends Bullet {
    SoilderBullet(Hero hero) {
        super(hero);
        damage = 25;
        speed = 20f;
        maxDistance = INFDISTANCE;
        maxTimeAlive = INFTIME;
        canBounce = false;
        accyuracy = 10f;
        spriteName = "Heroes/Soilder/bullet.png";
        scale = 0.4f;
        init();
    }
}
