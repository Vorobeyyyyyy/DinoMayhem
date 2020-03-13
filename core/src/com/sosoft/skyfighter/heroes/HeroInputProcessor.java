package com.sosoft.skyfighter.heroes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class HeroInputProcessor extends InputAdapter {
    Hero hero;
    int JUMP_KEY = Input.Keys.W;
    int LEFT_KEY = Input.Keys.A;
    int RIGHT_KEY = Input.Keys.D;
    int DUCK_KEY = Input.Keys.S;
    int SPELL1_KEY = Input.Keys.SPACE;
    int SPELL2_KEY = Input.Keys.CONTROL_LEFT;
    int SPELL3_KEY = Input.Keys.SHIFT_LEFT;


    public HeroInputProcessor(Hero hero_) {
        hero = hero_;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == JUMP_KEY)
            hero.state.jump = true;
        if (keycode == LEFT_KEY)
            hero.state.left = true;
        if (keycode == RIGHT_KEY)
            hero.state.right = true;
        if (keycode == DUCK_KEY)
            hero.state.duck = true;
        if (keycode == SPELL1_KEY)
            hero.state.firstAbility = true;
        if (keycode == SPELL2_KEY)
            hero.state.secondAbility = true;
        if (keycode == SPELL3_KEY)
            hero.state.thirdAbility = true;

        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == JUMP_KEY)
            hero.state.jump = false;
        if (keycode == LEFT_KEY)
            hero.state.left = false;
        if (keycode == RIGHT_KEY)
            hero.state.right = false;
        if (keycode == DUCK_KEY)
            hero.state.duck = false;
        if (keycode == SPELL1_KEY)
            hero.state.firstAbility = false;
        if (keycode == SPELL2_KEY)
            hero.state.secondAbility = false;
        if (keycode == SPELL3_KEY)
            hero.state.thirdAbility = false;
        return super.keyUp(keycode);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }
}
