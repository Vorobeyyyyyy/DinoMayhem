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
    private final int SPELL1_KEY = Xbox.R_BUMPER;
    private final int SPELL2_KEY = Xbox.X;
    private final int SPELL3_KEY = Xbox.Y;

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
        if (buttonIndex == SPELL1_KEY)
            hero.state.firstAbility = true;
        if (buttonIndex == SPELL2_KEY)
            hero.state.secondAbility = true;
        if (buttonIndex == SPELL3_KEY)
            hero.state.thirdAbility = true;

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
        if (buttonIndex == SPELL1_KEY)
            hero.state.firstAbility = false;
        if (buttonIndex == SPELL2_KEY)
            hero.state.secondAbility = false;
        if (buttonIndex == SPELL3_KEY)
            hero.state.thirdAbility = false;
        return super.buttonUp(controller, buttonIndex);
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value) {
        hero.state.left = false;
        hero.state.right = false;
        hero.state.jump = false;
        hero.state.duck = false;
        if (value == PovDirection.east)
            hero.state.right = true;
        if (value == PovDirection.north)
            hero.state.jump = true;
        if (value == PovDirection.west)
            hero.state.left = true;
        if (value == PovDirection.south)
            hero.state.duck = true;

        if (value == PovDirection.northEast) {
            hero.state.jump = true;
            hero.state.right = true;
        }
        if (value == PovDirection.northWest) {
            hero.state.jump = true;
            hero.state.left = true;
        }
        if (value == PovDirection.southEast) {
            hero.state.duck = true;
            hero.state.right = true;
        }
        if (value == PovDirection.southWest) {
            hero.state.duck = true;
            hero.state.left = true;
        }
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
                hero.state.aimAngle = new Vector2(controller.getAxis(3), -value).angle();
                break;
            case 3:
                hero.state.aimAngle = new Vector2(value, -controller.getAxis(2)).angle();
                break;
            case 4:
                break;
        }
        return super.axisMoved(controller, axisIndex, value);
    }
}
