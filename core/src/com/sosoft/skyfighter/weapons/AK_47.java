package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;

import static com.sosoft.skyfighter.weapons.Bullet.INFDISTANCE;
import static com.sosoft.skyfighter.weapons.Bullet.INFTIME;

public class AK_47 extends Weapon {
    public AK_47(Hero hero) {
        super(hero);
        init();
    }

    @Override
    public void init() {
        initBullet();
        posOffset = new Vector2(0, -15);
        animation.addAnimation("idle", new AnimatedSprite("Weapons/AK-47/idle_", 1, 1, 1.5f, posOffset));
        animation.addAnimation("fire", new AnimatedSprite("Weapons/AK-47/fire/fire_", 5, 1 / 10f, 1.5f, posOffset));
        animation.setAnimation("idle");

        fireSound = Gdx.audio.newSound(Gdx.files.internal("Weapons/AK-47/Sound/fire.mp3"));
        takeSound = Gdx.audio.newSound(Gdx.files.internal("Weapons/AK-47/Sound/take.mp3"));

        name = "AK-47";
        maxAmmo = 30;
        shotsPerSec = 10;
        accuracy = 8 / 10f;
        reloadTime = 3.5f;
        super.init();
    }

    @Override
    public void fire() {
        super.fire();
    }

    @Override
    void initBullet() {
        super.initBullet();
        bulletDef.damage = 123;
        bulletDef.speed = 40f;
        bulletDef.maxDistance = INFDISTANCE;
        bulletDef.maxTimeAlive = INFTIME;
        bulletDef.canBounce = false;
        bulletDef.accyuracy = 0.9f;
        bulletDef.spriteName = "Heroes/Soilder/bullet.png";
        bulletDef.scale = 0.4f;
        bulletDef.density = 3f;
    }
}
