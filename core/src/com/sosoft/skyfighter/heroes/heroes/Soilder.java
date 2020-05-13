package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.AK_47;

public class Soilder extends Hero {


    public Soilder(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller, number);
        state.maxSpeed = 15;
        state.jumpHeight = 20;
        state.maxHealth = 1000;

        weapon = new AK_47(this);
        reset();
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    @Override
    public void firstAbility() {
        weapon.fire();
    }

    @Override
    public void secondAbility() {

    }

    @Override
    public void thirdAbility() {

    }
}
