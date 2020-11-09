package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.Weapon;

public class Imposter extends Hero {

    public Imposter(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller, number);
        state.maxSpeed = 15;
        state.jumpHeight = 15;
        state.maxHealth = 1000;
        state.maxAirJumps = 2;

        weapons.add(new Weapon(this, "AK-47"));
        weapons.add(new Weapon(this, "AWP"));
        weapon = weapons.first();


        animation.addAnimation("wait", new AnimatedSprite("Heroes/imposter/wait/desc.json"));
        animation.addAnimation("walk", new AnimatedSprite("Heroes/imposter/walk/desc.json"));
        animation.setAnimation("walk");
    }
}
