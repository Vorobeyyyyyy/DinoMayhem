package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.math.Vector2;

public class HeroControllerProcessor extends ControllerAdapter {
    Hero hero;
    private final int JUMP_KEY = Xbox.L_BUMPER;
    private final int LEFT_KEY = Xbox.DPAD_LEFT;
    private final int RIGHT_KEY = Xbox.DPAD_RIGHT;
    private final int DUCK_KEY = Xbox.DPAD_DOWN;
    private final int FIRE_KEY = Xbox.R_BUMPER;
    private final int RELOAD_KEY = Xbox.X;

    HeroControllerProcessor(Hero hero_) {
        hero = hero_;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex) {
        if (buttonIndex == JUMP_KEY) {
            hero.state.jump = true;
            if (!hero.state.grounded) {
                hero.state.airJump = true;
            }
        }
        if (buttonIndex == LEFT_KEY)
            hero.state.left = true;
        if (buttonIndex == RIGHT_KEY)
            hero.state.right = true;
        if (buttonIndex == DUCK_KEY)
            hero.state.duck = true;
        if (buttonIndex == FIRE_KEY)
            hero.state.fire = true;
        if (buttonIndex == RELOAD_KEY)
            hero.state.reload = true;

        return super.buttonDown(controller, buttonIndex);
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonIndex) {
        if (buttonIndex == JUMP_KEY) {
            hero.state.airJump = false;
            hero.state.jump = false;
        }
        if (buttonIndex == LEFT_KEY)
            hero.state.left = false;
        if (buttonIndex == RIGHT_KEY)
            hero.state.right = false;
        if (buttonIndex == DUCK_KEY)
            hero.state.duck = false;
        if (buttonIndex == FIRE_KEY)
            hero.state.fire = false;
        if (buttonIndex == RELOAD_KEY)
            hero.state.reload = false;
        return super.buttonUp(controller, buttonIndex);
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
        if (value == PovDirection.east)
            hero.state.prevWeapon = true;
        if (value == PovDirection.west)
            hero.state.nextWeapon = true;
        return super.povMoved(controller, povIndex, value);
    }

    @Override
    public boolean axisMoved(Controller controller, int axisIndex, float value) {
        switch (axisIndex) {
            case 0:
                if (value > 0.6f)
                    hero.state.duck = true;
                else
                    hero.state.duck = false;
                break;
            case 1:
                if (value > 0.6f)
                    hero.state.right = true;
                else
                    hero.state.right = false;
                if (value < -0.6f)
                    hero.state.left = true;
                else
                    hero.state.left = false;
                break;
            case 2:
            case 3:
                float axisX = controller.getAxis(3);
                float axisY = controller.getAxis(2);
                if (Math.abs(axisX) < 0.05) axisX = 0;
                if (Math.abs(axisY) < 0.05) axisY = 0;
                if (Math.abs(axisX) > 0.2 || Math.abs(axisY) > 0.2)
                    hero.state.aimAngle = new Vector2(axisX, -axisY).angle();
                hero.updateAimAngle();
                break;
            case 4:
                break;
        }
        return super.axisMoved(controller, axisIndex, value);
    }
}
