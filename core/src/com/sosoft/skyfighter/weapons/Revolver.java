package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;

import static com.sosoft.skyfighter.weapons.Bullet.INFDISTANCE;
import static com.sosoft.skyfighter.weapons.Bullet.INFTIME;

public class Revolver extends Weapon {
    public Revolver(Hero hero) {
        super(hero);
        init();
    }

    @Override
    public void init() {
        initBullet();
        posOffset = new Vector2(0, -10);
        animation.addAnimation("idle", new AnimatedSprite("Weapons/Revolver/idle_", 1, 1, 1.5f, posOffset));
        animation.addAnimation("fire", new AnimatedSprite("Weapons/Revolver/fire/fire_", 6, 1 / 10f, 1.5f, posOffset));
        animation.setAnimation("idle");

        fireSound = Gdx.audio.newSound(Gdx.files.internal("Weapons/Revolver/Sound/fire.mp3"));

        name = "Revolver";
        maxAmmo = 6;
        shotsPerSec = 6;
        accuracy = 0.95f;
        reloadTime = 3.5f;
        super.init();
    }

    @Override
    void initBullet() {
        super.initBullet();
        bulletDef.damage = 228;
        bulletDef.speed = 60f;
        bulletDef.maxDistance = INFDISTANCE;
        bulletDef.maxTimeAlive = INFTIME;
        bulletDef.canBounce = false;
        bulletDef.accyuracy = 0.9f;
        bulletDef.spriteName = "Heroes/Soilder/bullet.png";
        bulletDef.scale = 0.4f;
        bulletDef.density = 3f;
    }
}
