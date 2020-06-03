package com.sosoft.skyfighter.heroes.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.sosoft.skyfighter.heroes.Hero;
import com.sosoft.skyfighter.levels.LevelController;
import com.sosoft.skyfighter.weapons.AK_47;
import com.sosoft.skyfighter.weapons.Revolver;
import com.sosoft.skyfighter.weapons.SVD;

public class Soilder extends Hero {


    public Soilder(LevelController levelController, float posX, float posY, Controller controller, int number) {
        super(levelController, posX, posY, controller, number);
        state.maxSpeed = 15;
        state.jumpHeight = 15;
        state.maxHealth = 1000;
        state.maxAirJumps = 2;

        weapons.add(new AK_47(this));
        weapons.add(new Revolver(this));
        weapons.add(new SVD(this));

        weapon = weapons.first();

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

}
