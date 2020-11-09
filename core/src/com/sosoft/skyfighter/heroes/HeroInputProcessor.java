package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class HeroInputProcessor extends InputAdapter {
    Hero hero;
    int JUMP_KEY = Input.Keys.W;
    int LEFT_KEY = Input.Keys.A;
    int RIGHT_KEY = Input.Keys.D;
    int DUCK_KEY = Input.Keys.S;
    int FIRE_KEY = Input.Buttons.LEFT;
    int RELOAD_KEY = Input.Keys.R;
    //int NEXT_WEAPON_KEY = Input.Buttons.FORWARD;
    int NEXT_WEAPON_KEY = Input.Keys.E;
    //int PREV_WEAPON_KEY = Input.Buttons.BACK;
    int PREV_WEAPON_KEY = Input.Keys.Q;



    public HeroInputProcessor(Hero hero_) {
        hero = hero_;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == JUMP_KEY) {
            hero.state.jump = true;
            if (!hero.state.grounded) {
                hero.state.airJump = true;
            }
        }
        if (keycode == LEFT_KEY)
            hero.state.left = true;
        if (keycode == RIGHT_KEY)
            hero.state.right = true;
        if (keycode == DUCK_KEY)
            hero.state.duck = true;
        if (keycode == FIRE_KEY)
            hero.state.fire = true;
        if (keycode == RELOAD_KEY)
            hero.state.reload = true;
        if (keycode == NEXT_WEAPON_KEY)
            hero.state.nextWeapon = true;
        if (keycode == PREV_WEAPON_KEY)
            hero.state.prevWeapon = true;

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == JUMP_KEY) {
            hero.state.jump = false;
            hero.state.airJump = false;
        }
        if (keycode == LEFT_KEY)
            hero.state.left = false;
        if (keycode == RIGHT_KEY)
            hero.state.right = false;
        if (keycode == DUCK_KEY)
            hero.state.duck = false;
        if (keycode == FIRE_KEY)
            hero.state.fire = false;
        if (keycode == RELOAD_KEY)
            hero.state.reload = false;
        if (keycode == NEXT_WEAPON_KEY)
            hero.state.nextWeapon = false;
        if (keycode == PREV_WEAPON_KEY)
            hero.state.prevWeapon = false;
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == FIRE_KEY)
            hero.state.fire = true;
        if (button == NEXT_WEAPON_KEY)
            hero.state.nextWeapon = true;
        if (button == PREV_WEAPON_KEY)
            hero.state.prevWeapon = true;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == FIRE_KEY)
            hero.state.fire = false;
        if (button == NEXT_WEAPON_KEY)
            hero.state.nextWeapon = false;
        if (button == PREV_WEAPON_KEY)
            hero.state.prevWeapon = false;
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
