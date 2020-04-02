package com.sosoft.skyfighter.weapons;

abstract public class Weapon {
    // Characteristics
    String name;
    int maxAmmo;
    int currentAmmo;
    int shotsPerSec;
    float accuracy;
    //
    float cooldown;

    void update(float delta) {
        if (cooldown > 0)
            cooldown -= delta;
    }

    void shoot(float direction) {
        cooldown = 1f / shotsPerSec;
    }



}
