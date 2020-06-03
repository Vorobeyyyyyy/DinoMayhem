package com.sosoft.skyfighter.weapons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.sosoft.skyfighter.Animation;
import com.sosoft.skyfighter.heroes.Hero;

abstract public class Weapon {
    // Characteristics
    public String name;
    int maxAmmo;
    int shotsPerSec;
    float accuracy;

    BulletDef bulletDef;
    float cooldown;
    int currentAmmo;
    Hero hero;
    public Animation animation;
    Vector2 posOffset;
    public float direction;

    Weapon(Hero hero) {
        currentAmmo = maxAmmo;
        animation = new Animation();
        this.hero = hero;
    }


    public void update(float delta) {
        if (cooldown > 0)
            cooldown -= delta;
        else
            cooldown = 0f;
        animation.update(delta);
        animation.pos.x -= animation.currentAnimation.size.x / 2;
        animation.pos.y -= animation.currentAnimation.size.y / 2;
    }

    public void fire() {
        if (cooldown <= 0) {
            cooldown = 1f / (float) shotsPerSec;
            currentAmmo -= 1;
            animation.playAmination("fire");
            hero.levelController.level.bullets.add(new Bullet(this, bulletDef));
        }
    }

    public void draw(Batch batch) {
        animation.draw(batch);
    }

    void initBullet() {
        bulletDef = new BulletDef();
    }

}
