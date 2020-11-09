package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;

import static com.sosoft.skyfighter.weapons.Bullet.INFDISTANCE;
import static com.sosoft.skyfighter.weapons.Bullet.INFTIME;

public class SVD extends Weapon {
    public SVD(Hero hero) {
        super(hero);
        init();
    }

    @Override
    public void init() {
        initBullet();
        posOffset = new Vector2(0, -5);
        animation.addAnimation("idle", new AnimatedSprite("Weapons/SVD/idle_", 1, 1, 1.5f, posOffset));
        animation.addAnimation("fire", new AnimatedSprite("Weapons/SVD/fire/fire_", 11, 1 / 10f, 1.5f, posOffset));
        animation.setAnimation("idle");

        fireSound = Gdx.audio.newSound(Gdx.files.internal("Weapons/SVD/Sound/fire.mp3"));

        name = "SVD";
        maxAmmo = 10;
        shotsPerSec = 2;
        accuracy = 1f;
        reloadTime = 3.5f;
        super.init();
    }

    @Override
    void initBullet() {
        super.initBullet();
        bulletDef.damage = 322;
        bulletDef.speed = 80f;
        bulletDef.maxDistance = INFDISTANCE;
        bulletDef.maxTimeAlive = INFTIME;
        bulletDef.canBounce = false;
        bulletDef.accuracy = 1f;
        bulletDef.spriteName = "Heroes/Soilder/bullet.png";
        bulletDef.scale = 0.4f;
        bulletDef.density = 9f;
    }
}
