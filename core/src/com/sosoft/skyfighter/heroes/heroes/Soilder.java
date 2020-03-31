package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;

public class Soilder extends Hero {
    int bulletCount = 30;
    int bulletCurrentCount = 30;
    float ReloadTime = 2f;


    public Soilder(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller,number);
        state.firstAbilityCooldown = 1/10f;
        state.maxSpeed = 15;
        state.jumpHeight = 20;
        state.maxHealth = 1000;
        reset();
    }


    @Override
    public void firstAbility() {
        levelController.level.bullets.add(new SoilderBullet(this));
    }

    @Override
    public void secondAbility() {

    }

    @Override
    public void thirdAbility() {

    }
}
