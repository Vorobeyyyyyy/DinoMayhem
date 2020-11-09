package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.sosoft.skyfighter.AnimatedSprite;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.Weapon;

public class Imposter extends Hero {
    public static final String WAIT_PATH = "Heroes/imposter/wait/desc.json";
    public static final String WALK_PATH = "Heroes/imposter/walk/desc.json";

    public Imposter(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller, number);
        state.maxSpeed = 15;
        state.jumpHeight = 15;
        state.maxHealth = 1000;
        state.maxAirJumps = 2;

        weapons.add(new Weapon(this, "AK-47"));
        weapons.add(new Weapon(this, "AWP"));
        weapons.add(new Weapon(this, "Revolver"));
        weapons.add(new Weapon(this, "SVD"));
        weapon = weapons.first();


        animation.addAnimation("wait", new AnimatedSprite(WAIT_PATH));
        animation.addAnimation("walk", new AnimatedSprite(WALK_PATH));
        animation.setAnimation("walk");
    }
}
