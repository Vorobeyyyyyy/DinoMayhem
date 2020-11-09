package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.Weapon;

public class Soilder extends Hero {

    public Soilder(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller, number);
        state.maxSpeed = 15;
        state.jumpHeight = 15;
        state.maxHealth = 1000;
        state.maxAirJumps = 2;

        weapons.add(new Weapon(this, "AK-47"));
        weapon = weapons.first();


        animation.addAnimation("wait", new AnimatedSprite("Heroes/dino/walk/desc.json"));
        animation.addAnimation("walk", new AnimatedSprite("Heroes/dino/walk/desc.json"));
        animation.setAnimation("walk");
    }
}
