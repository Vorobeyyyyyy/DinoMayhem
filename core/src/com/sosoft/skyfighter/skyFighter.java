package com.sosoft.skyfighter;

import com.badlogic.gdx.Game;
import com.sosoft.skyfighter.levels.Menu;

public class skyFighter extends Game {
    @Override
    public void create() {
        setScreen(new Menu(this));
    }
}
