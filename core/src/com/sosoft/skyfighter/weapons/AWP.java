package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;

import static com.sosoft.skyfighter.weapons.Bullet.INFDISTANCE;
import static com.sosoft.skyfighter.weapons.Bullet.INFTIME;

public class AWP extends Weapon {
    public AWP(Hero hero) {
        super(hero);
        init();
    }

    @Override
    public void init() {
        initBullet();
        posOffset = new Vector2(0,2);
        animation.addAnimation("idle", new AnimatedSprite("Weapons/AWP/idle_",1,1,1.7f, posOffset));
        animation.addAnimation("fire", new AnimatedSprite("Weapons/AWP/fire/fire_",10,2/10f,1.7f, posOffset));
        animation.setAnimation("idle");

        fireSound = Gdx.audio.newSound(Gdx.files.internal("Weapons/AWP/Sound/fire.mp3"));

        name = "AWP";
        maxAmmo = 10;
        shotsPerSec = 1;
        accuracy = 1f;
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
        bulletDef.damage = 1337;
        bulletDef.speed = 160f;
        bulletDef.maxDistance = INFDISTANCE;
        bulletDef.maxTimeAlive = INFTIME;
        bulletDef.canBounce = false;
        bulletDef.accyuracy = 1f;
        bulletDef.spriteName = "Heroes/Soilder/bullet.png";
        bulletDef.scale = 0.4f;
        bulletDef.density = 9f;
    }
}
